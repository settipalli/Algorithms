#!/usr/bin/env python

# ========================================================================
# Name          :   gnomesort.py
#
# Description   :   This script implements the gnome sort that was first
#                   implemented by Hamid Sarbazi-Azad in the year 2000.
#
# Version       :   1.0.0
#
# Author        :   Santhoshkumar Settipalli
#
# License       :   The MIT License (MIT).
#
# Change log    :
#   20-Sep-2013 :   Santhoshkumar Settipalli (Santhoshkumar.Settipalli@polycom.com)
#                   Initial version.
#
# Copyright (c) 2013 Santhoshkumar Settipalli
# ========================================================================

import os
import sys
import fcntl
import logging
import logging.handlers
from optparse import OptionParser
import settings


class GnomeSort:

    """
    Based on the input specified by the user, GnomeSort can be utilized to sort
    values in ascending or descending order.
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
        parser.add_option("-f", "--file", dest="inputfile",
                          help="provide the absolute path to the file whose contents have to be sorted.",
                          metavar="FILE")
        parser.add_option("-i", "--integersort", dest="integersort",
                          action="store_true", default=False,
                          help="choosing this option converts the contents read from the input file as integers and sorts them based on numeric value.")
        parser.add_option("-r", "--reversesort", dest="reversesort",
                          action="store_true", default=False,
                          help="choosing this option sorts the contents in descending order while the default sort order is ascending.")

        opts, args = parser.parse_args()

        try:
            if not os.path.isfile(opts.inputfile):
                self.log(
                    level="error", msg="Invalid input file.")
                sys.exit(1)
        except:
            self.log(
                level="error", msg="Unknown failure.")
            sys.exit(1)

        return opts

    # === FUNCTION ===========================================================
    # Name                : sort
    #
    # Description         : is used to perform the actual intended operation of
    #                       this program - sorting a sequence of contents
    #                       ascending/descending.
    #
    # Return value        : none.
    #
    # Since               : v1.0
    # ========================================================================
    def sort(self, seq, asc=True):
        i = 0
        while i < len(seq):
            if i == 0 or seq[i - 1] <= seq[i]:
                i += 1
            else:
                seq[i], seq[i - 1] = seq[i - 1], seq[i]
                i -= 1

        if not asc:
            seq.reverse()

        self.log(level="info",msg="Sequence sorted.")


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
        self.log(level="info", msg="GnomeSort: Logger initialized.")

        self.log(
            level="info", msg="Parsing command line arguments.")

        opts = self.options()

        # Read the input from the file.
        seq = []
        with open(opts.inputfile, 'r') as f:
            try:
                seq = f.readlines()
            except:
                self.log(
                    level="error", msg="Failed to read the contents of the file.")
                sys.exit(1)

        # Convert the input to integers if integersort is specified.
        if opts.integersort:
            try:
                for i in xrange(0, len(seq)):
                    seq[i] = int(seq[i])

            except:
                self.log("error", msg="Failed to transform some of the input data as an integer.")
                sys.exit(1)

        asc = False if opts.reversesort else True

        self.sort(seq, asc=asc)

        # Write output to the file
        with open(opts.inputfile + ".sorted", 'w') as f:
            try:
                for value in seq:
                    f.write("%s\n" % value)
                self.log(level="info", msg="Successfully transferred sorted contents to the file.")
            except:
                self.log(
                    level="error", msg="Failed to write the sorted contents of the file.")
                sys.exit(1)


        self.log(level="info", msg="All done!")
        self.log(level="info",
                 msg="=============================================")


if __name__ == "__main__":
    sort = GnomeSort()
    sort.main()
