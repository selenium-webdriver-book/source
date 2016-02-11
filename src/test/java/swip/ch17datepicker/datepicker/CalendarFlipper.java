package swip.ch17datepicker.datepicker;

enum CalendarFlipper {
    YEAR_FLIPPER {
        @Override
        void next(Calendar calendar) {
            calendar.nextYear();
        }

        @Override
        void previous(Calendar calendar) {
            calendar.previousYear();
        }

        @Override
        int displayedValue(Calendar calendar) {
            return calendar.displayedYear();
        }
    },
    MONTH_FLIPPER {
        @Override
        void next(Calendar calendar) {
            calendar.nextMonth();
        }

        @Override
        void previous(Calendar calendar) {
            calendar.previousMonth();
        }

        @Override
        int displayedValue(Calendar calendar) {
            return calendar.displayedMonth();
        }
    };

    abstract void next(Calendar calendar);

    abstract void previous(Calendar calendar);

    abstract int displayedValue(Calendar calendar);

    /**
     * this method is shared both both YEAR_FLIPPER and MONTH_FLIPPER.
     * When called from YEAR_FLPPER, the displayValue(calendar), next(calendar)
     * and previous(calendar) use the implementations in YEAR_FLIPPER. This is
     * the Template Method Pattern in the Design Patterns book.
     *
     * @param calendar the calendar to flip
     * @param flipTo   the year or month to flip to
     */
    void flip(Calendar calendar, int flipTo) {
        int difference = displayedValue(calendar) - flipTo;
        if (difference < 0) {
            for (int i = difference; i < 0; i++) {
                next(calendar);
            }
        } else if (difference > 0) {
            for (int i = 0; i < difference; i++) {
                previous(calendar);
            }
        }
    }
}
