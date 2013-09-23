#!/usr/bin/env python

# ========================================================================
# Name          :   checkboard_covering.py
#
# Description   :   This script implements one of the possible algorithms that
#                   uses the pricinples of induction and recursion to solve a
#                   classic fuzzle:
#                       How do you cover a checkboard that has one corner square
#                   missing, using L-shaped tiles?
#
# Version       :   1.0.0
#
# Author        :   Santhoshkumar Settipalli
#
# License       :   The MIT License (MIT).
#
# Change log    :
#   23-Sep-2013 :   Santhoshkumar Settipalli (Santhoshkumar.Settipalli@polycom.com)
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


class CheckboardCovering:

    """
    Principle of induction and recursion are utilized to solve a classic puzzle
    on covering a checkboard that has one corner square missing using L-shaped
    tiles.
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
    # Name                : is_power_of_2
    #
    # Description         : An utility function that return true if the given
    #                       number is a result of raising numeral two to the
    #                       power of some other number usually less than the
    #                       given number.
    #
    # Return value        : True - if the given number is a result of raising 
    #
    # Since               : v1.0
    # ========================================================================
    def is_power_of_2(self, num):
        return ((num & num-1) == 0 and num != 0)

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
        parser.add_option("-s", "--boardsize", dest="size", type="int",
                          help="provide the size of the board - should be a number that is a power of 2.")

        opts, args = parser.parse_args()

        if not self.is_power_of_2(opts.size):
            self.log(
                level="error", msg="Invalid size. The value should be a number that is a power of 2.")
            sys.exit(1)

        return opts

    # === FUNCTION ===========================================================
    # Name                : cover
    #
    # Description         : is used to perform the actual intended operation of
    #                       this program - implementing a solution for classic
    #                       checkboard covering.
    #
    # Return value        : none.
    #
    # Since               : v1.0
    # ========================================================================
    def cover(self, board, label=1, top=0, left=0, side=None):
        if side is None:
            side = len(board)  # for 8x8 board, len = 8

        # Side length of subboard
        s = side // 2 # for a 8x8 board, side = 4

        # Offsets for outer/inner squares of subboards:
        offsets = (0, -1), (side-1, 0)

        for dy_outer, dy_inner in offsets:      # dy_outer = (0, -1); dy_inner = (3, 0)
            for dx_outer, dx_inner in offsets:  # dx_outer = (0, -1); dx_inner = (3, 0)
                # If the outer corner is not set ...
                if not board[top+dy_outer][left+dx_outer]: # board[0][-1]
                    # .. label the inner corner
                    board[top+s+dy_inner][left+s+dx_inner] = label

        # Next label:
        label += 1
        if s > 1:
            for dy in [0, s]:
                for dx in [0, s]:
                    # Recursive calls, if s is at least 2:
                    label = self.cover(board, label, top+dy, left+dx, s)

        # Return the next available label:
        return label

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
        self.log(level="info", msg="Checkboard Covering: Logger initialized.")

        self.log(
            level="info", msg="Parsing command line arguments.")

        opts = self.options()

        board = [[0]*opts.size for i in range(opts.size)]

        self.cover(board)

        print "Final Output:"
        for row in board:
            print "  %2i" * 8 % tuple(row)

        self.log(level="info", msg="All done!")
        self.log(level="info",
                 msg="=============================================")


if __name__ == "__main__":
    cc = CheckboardCovering()
    cc.main()
