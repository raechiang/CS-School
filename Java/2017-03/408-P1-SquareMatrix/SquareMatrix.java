
public class SquareMatrix
{
    private int size;
    private double[][] matrix;
    
    public SquareMatrix(int size, double[][] arr)
    {
        this.size = size;
        matrix = new double[size][size];
        for (int row = 0; row < size; ++row)
        {
            for (int col = 0; col < size; ++col)
            {
                matrix[row][col] = arr[row][col];
            }
        }
    }
    
    private boolean checkSize(int otherSize)
    {
        if (this.size == otherSize)
        {
            return true;
        }
        return false;
    }
    
    public SquareMatrix addition(SquareMatrix other)
    {
        if (checkSize(other.getDimension()))
        {
            double[][] sumArr = new double[size][size];
            for (int row = 0; row < size; ++row)
            {
                for (int col = 0; col < size; ++col)
                {
                    sumArr[row][col] = matrix[row][col] + other.getEntry(row, col);
                }
            }
            return (new SquareMatrix(size, sumArr));
        }
        return null;
    }
    
    private double getEntry(int row, int col)
    {
        if (checkBound(row, col))
        {
            return matrix[row][col];
        }
        
        return -1;
    }
    
    private boolean checkBound(int row, int col)
    {
        if (row < 0 || col < 0)
        {
            System.out.println("Out of bounds.");
            return false;
        }
        if (row >= size || col >= size)
        {
            System.out.println("Out of bounds.");
            return false;
        }
        
        return true;
    }
    
    public SquareMatrix subtraction(SquareMatrix other)
    {
        if (checkSize(other.getDimension()))
        {
            double[][] diffArr = new double[size][size];
            for (int row = 0; row < size; ++row)
            {
                for (int col = 0; col < size; ++col)
                {
                    diffArr[row][col] = matrix[row][col] - other.getEntry(row, col);
                }
            }
            return (new SquareMatrix(size, diffArr));
        }
        return null;
    }
    
    public SquareMatrix multiplication(SquareMatrix other)
    {
        // Matrix multiplication is allowed with matrices with
        // different dimensions if the number of columns of the
        // first one has the same number of rows of the second
        // one. That is, if A and B are of dimensions n*m and m*p
        // respectively, A * B is valid (m=m), but B * A is not (p!=n).
        // Since this project uses only square matrices, we can assume
        // that as long as both have the same "size", it's valid
        double[][] result = new double[size][size];
        if (checkSize(other.getDimension()))
        {
            for (int i = 0; i < size; ++i)
            {
                for (int j = 0; j < size; ++j)
                {
                    for (int k = 0; k < size; ++k)
                    {
                        result[i][j] += matrix[i][k] * other.getEntry(k, j);
                    }
                }
            }
            return (new SquareMatrix(size, result));
        }
        return null;
    }
    
    public void write()
    {
        for (int i = 0; i < size; ++i)
        {
            for (int j = 0; j < size; ++j)
            {
                System.out.printf("%10.3f ", matrix[i][j]);
            }
            System.out.println();
        }
    }
    
    public int getDimension()
    {
        return size;
    }
}
