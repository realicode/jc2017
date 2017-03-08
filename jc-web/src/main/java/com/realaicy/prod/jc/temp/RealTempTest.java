package com.realaicy.prod.jc.temp;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.realaicy.prod.jc.uitl.OtherUtil;

import java.time.LocalDate;

/**
 * Created by realaicy on 2017/3/8.
 * xx
 */
public class RealTempTest {
    public static void main(String[] args) {
        Table<Integer, LocalDate, Integer> table = HashBasedTable.create();
        LocalDate dayAfter5 = LocalDate.now().plusDays(5L);
        System.out.println(dayAfter5);

        for (int row = 1; row < 5; row++) {

            for (LocalDate now = LocalDate.now(); now.isBefore(LocalDate.now().plusDays(5L)); now = now.plusDays(1L)) {
                table.put(row, now, OtherUtil.getRandNum(1, 5));
            }
        }


        System.out.println(table.row(1));
        System.out.println(table.row(2));

        System.out.println(table.row(5));
        System.out.println(table.contains(2, LocalDate.now().plusDays(1L)));
        System.out.println(table.contains(6, LocalDate.now().plusDays(1L)));
        System.out.println(table.contains(2, LocalDate.now().plusDays(7L)));
        System.out.println(table.contains(6, LocalDate.now().plusDays(7L)));

        table.put(6, LocalDate.now().plusDays(7L), table.get(6, LocalDate.now().plusDays(7L)));
        System.out.println("newenew : "  + table.contains(6, LocalDate.now().plusDays(7L)));


    }
}
