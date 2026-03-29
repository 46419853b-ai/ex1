package expense;

import java.util.Date;
import java.util.List;

public class ExpenseReport {

    public void printReport(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;

        System.out.print("Expenses " + new Date() + "\n");

        for (Expense expense : expenses) {
            if (isMealExpense(expense)) {
                mealExpenses += expense.amount;
            }

            String expenseName = getExpenseName(expense);

            if ((expense.type == ExpenseType.DINNER && expense.amount > 5000)
                    || (expense.type == ExpenseType.BREAKFAST && expense.amount > 1000)) {
                System.out.print(expenseName + "\t" + expense.amount + "\tX\n");
            } else {
                System.out.print(expenseName + "\t" + expense.amount + "\n");
            }

            total += expense.amount;
        }

        System.out.print("Meal expenses: " + mealExpenses + "\n");
        System.out.print("Total expenses: " + total + "\n");
    }

    private boolean isMealExpense(Expense expense) {
        return expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST;
    }

    private String getExpenseName(Expense expense) {
        return switch (expense.type) {
            case DINNER -> "Dinner";
            case BREAKFAST -> "Breakfast";
            case CAR_RENTAL -> "Car Rental";
        };
    }
}
