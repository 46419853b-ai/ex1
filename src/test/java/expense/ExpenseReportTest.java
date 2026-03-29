package expense;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;

public class ExpenseReportTest {

    String expected = String.format("""
            Expenses %s
            Dinner	5000
            Dinner	5001	X
            Breakfast	1000
            Breakfast	1001	X
            Car Rental	200
            Meal expenses: 12002
            Total expenses: 12202
            """, new Date());

    @Test
    public void testExpenseReport() throws IOException {
        //Redirect System.out to buffer
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));

        var expenses = List.of(
                createExpense(ExpenseType.DINNER, 5000),
                createExpense(ExpenseType.DINNER, 5001),
                createExpense(ExpenseType.BREAKFAST, 1000),
                createExpense(ExpenseType.BREAKFAST, 1001),
                createExpense(ExpenseType.CAR_RENTAL, 200)
        );
        new ExpenseReport().printReport(expenses);

        bo.flush();
        String allWrittenLines = bo.toString();
        Assertions.assertEquals(expected, allWrittenLines);
    }

    private Expense createExpense(ExpenseType expenseType, int amount) {
        var expense = new Expense();
        expense.type = expenseType;
        expense.amount = amount;
        return expense;
    }
}