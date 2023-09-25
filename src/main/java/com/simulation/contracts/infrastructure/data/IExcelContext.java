package com.simulation.contracts.infrastructure.data;

import java.util.List;

public interface IExcelContext {

	IExcelContext fromRowIndexOf(int initialRow);

    List<Double> readDecimal(String sheetName, String header);

    List<Integer> readInteger(String sheetName, String header);
}