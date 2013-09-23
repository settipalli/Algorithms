#!/usr/bin/env python

# ========================================================================
# Name          :   generate_test_data.py
#
# Description   :   This script can be utilized to generate test data and store
#                   in a file specified by the user so that it can be used as
#                   input for the mergesort and gnomesort programs.
#
# Version       :   1.0.0
#
# Author        :   Santhoshkumar Settipalli
#
# License       :   The MIT License (MIT).
#
# Change log    :
#   20-Sep-2013 :    Santhoshkumar Settipalli (Santhoshkumar.Settipalli@polycom.com)
#                    Initial version.
#
# Copyright (c) 2013 Santhoshkumar Settipalli
# ========================================================================

import os
import sys
import fcntl
import logging
import logging.handlers
from optparse import OptionParser
import time
import random
import settings


class TestData:

    """
    An object of this class can be utilized to generate random test data which
    can later be utilized by other utilities.
    """

    # Reference to logging modules.
    logger = None
    handler = None

    logging_levels = {
        'debug': logging.DEBUG,
        'info': logging.INFO,
        'warning': logging.WARNING,
        'error': logging.ERROR,
        'critical': logging.CRITICAL
    }

    # === FUNCTION ===========================================================
    # Name                : log
    #
    # Description         : Is used to log messages into a file (configured in
    #                       logger) - default log message level would be 'debug'.
    #
    # Return value        : none.
    #
    # Since               : v1.0
    # ========================================================================

    def log(self, level='debug', msg=""):
        self.logger.log(self.logging_levels.get(level), msg)

    # === FUNCTION ===========================================================
    # Name                : run_once
    #
    # Description         : Is used to make sure that only one instance of the
    #                       current program in executed at any point of time.
    #
    # Return value        : none.
    #
    # Since               : v1.0
    # ========================================================================
    def run_once(self):
        global fh
        fh = open(os.path.realpath(__file__), 'r')
        try:
            fcntl.flock(fh, fcntl.LOCK_EX | fcntl.LOCK_NB)
        except:
            print "Multiple instance of the program are not supported."
            os._exit(1)

    # === FUNCTION ===========================================================
    # Name                : options
    #
    # Description         : is used to parse the command line options provided
    #                       by user.
    #
    # Return value        : none.
    #
    # Since               : v1.0
    # ========================================================================
    def options(self):
        parser = OptionParser()
        parser.add_option("-f", "--outputfile", dest="outputfile",
                          help="provide the absolute path to the file to which the test data has to be written.",
                          metavar="FILE")
        parser.add_option("-d", "--double", dest="double",
                          action="store_true", default=False,
                          help="choosing this option if the test data generated should be floating point numbers rather than integers.")
        parser.add_option("-c", "--count", dest="count",
                          type="int", default=10000,
                          help="provide the total number of test data entities to be created (default is 10000).")
        parser.add_option("-s", "--startrange", dest="startrange",
                          type="int", default=0,
                          help="provide the start range for the test data (default is 0).")
        parser.add_option("-e", "--endrange", dest="endrange",
                          type="int", default=10000,
                          help="provide the end range for the test data (default is 10000).")
        parser.add_option("-p", "--precision", dest="precision",
                          type="int", default=2,
                          help="provide the count for number of digits to be considered after decimal point in case floating point numbers are generated (default is 2).")

        opts, args = parser.parse_args()

        try:
            if os.path.isfile(opts.outputfile):
                self.log(
                    level="error", msg="File: %s exists. Deleting it so that new data can be written to it." % opts.outputfile)
                os.remove(opts.outputfile)

            if opts.endrange <= opts.startrange:
                self.log(level="error",
                         msg="endrange should be more than startrange. It cannot be less or equal.")
                sys.exit(1)
        except:
            self.log(
                level="error", msg="Unknown failure.")
            sys.exit(1)

        return opts

    # === FUNCTION ===========================================================
    # Name                : create_data
    #
    # Description         : is used to create the required test data and store
    #                       it to a file specified by the users.
    #
    # Return value        : none.
    #
    # Since               : v1.0
    # ========================================================================
    def create_data(self, opts):
        random.seed(time.time())

        try:
            with open(opts.outputfile, 'w') as f:
                for i in xrange(0, opts.count):
                    value = random.random() * \
                        (opts.endrange - opts.startrange) + \
                        opts.startrange
                    if not opts.double:
                        value = long(value)
                    else:
                        value = round(value, opts.precision)
                    f.write("%s\n" % value)

            self.log(level="info",
                     msg="Successfully transferred the random test data to %s." % opts.outputfile)
        except Exception as e:
            self.log(level="error",
                     msg="Failed to write random test data to %s. Exception: %s" % (opts.outputfile, e))
            sys.exit(1)

    # === FUNCTION ===========================================================
    # Name                : main
    #
    # Description         : Executes the core purpose of this program.
    #
    # Return value        : none.
    #
    # Since               : v1.0
    # ========================================================================
    def main(self):
        # Make sure that you are the only instance in execution.
        self.run_once()

        # Setup logging.
        logging.basicConfig(format='%(asctime)s - %(levelname)s - %(message)s',
                            datefmt='%Y-%m-%d %H:%M:%S')
        self.logger = logging.getLogger('branch_mapper')
        self.logger.setLevel(self.logging_levels.get(settings.LOG_LEVEL))

        # Add the log message handler to the logger (1 MB per file and upto 10
        # files)
        self.handler = logging.handlers.RotatingFileHandler(
            settings.LOG_FILE, maxBytes=1048576, backupCount=10)
        self.handler.setFormatter(logging.Formatter(
            fmt='%(asctime)s - %(levelname)s - %(message)s', datefmt='%Y-%m-%d %H:%M:%S'))
        self.logger.addHandler(self.handler)

        # Avoid console logging - by blocking automatic propagation to the upper
        # logger module i.e. console logger.
        # self.logger.propagate = False

        self.log(level="info",
                 msg="=============================================")
        self.log(level="info", msg="Generate Test Data: Logger initialized.")

        self.log(
            level="info", msg="Parsing command line arguments.")

        opts = self.options()

        self.create_data(opts)

        self.log(level="info", msg="All done!")
        self.log(level="info",
                 msg="=============================================")


if __name__ == "__main__":
    test_data = TestData()
    test_data.main()
