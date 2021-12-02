package controller;

/**
 * Index of a block
 */
public class BlockIndex {
    private int row;

    private int column;

    /**
     * Constructor for a block index object
     * 
     * @param row
     * @param column
     */
    public BlockIndex(int row, int column) {
        super();

        this.row = row;
        this.column = column;
    }

    /**
     * Getter for the row of the block
     * 
     * @return the row nummber
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Getter for the column of the block
     * 
     * @return the column nummber
     */
    public int getColumn() {
        return this.column;
    }
}