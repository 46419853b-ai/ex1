package expense;

import java.util.Date;
import java.util.List;

public class ExpenseReport {

    public void printReport(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;

        printHeader();

        for (Expense expense : expenses) {
            if (isMealExpense(expense)) {
                mealExpenses += expense.amount;
            }

            printExpense(expense);
            total += expense.amount;
        }

        printTotals(mealExpenses, total);
    }

    private void printHeader() {
        System.out.print("Expenses " + new Date() + "\n");
    }

    private boolean isMealExpense(Expense expense) {
        return expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST;
    }

    private boolean isOverLimit(Expense expense) {
        return (expense.type == ExpenseType.DINNER && expense.amount > 5000)
                || (expense.type == ExpenseType.BREAKFAST && expense.amount > 1000);
    }

    private void printExpense(Expense expense) {
        String expenseName = getExpenseName(expense);

        if (isOverLimit(expense)) {
            System.out.print(expenseName + "\t" + expense.amount + "\tX\n");
        } else {
            System.out.print(expenseName + "\t" + expense.amount + "\n");
        }
    }

    private String getExpenseName(Expense expense) {
        switch (expense.type) {
            case DINNER:
                return "Dinner";
            case BREAKFAST:
                return "Breakfast";
            case CAR_RENTAL:
                return "Car Rental";
            default:
                return "Unknown";
        }
    }

    private void printTotals(int mealExpenses, int total) {
        System.out.print("Meal expenses: " + mealExpenses + "\n");
        System.out.print("Total expenses: " + total + "\n");
    }
}