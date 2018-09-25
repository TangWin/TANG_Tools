package com.tang.core;

import com.tang.util.FileUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 将excal表导出成文本文件
 * @author TW
 */
public class ExportData {

    private static final String ROOT = "I:\\桌面\\其他\\";

    private static final String FILE_PATH = "I:\\桌面\\其他\\机器人信息修改.xls";

    private static List<String> headRow = new ArrayList<>();


    public static void main(String[] args) {

        List<UsersArches> usersArchesList = getFile();

        StringBuilder sb = new StringBuilder();

        for (UsersArches usersArches : usersArchesList) {
            sb.append("UPDATE  T_USERSARCHES SET " + headRow.get(1) + "= '" + usersArches.getNickname() + "', ");
            sb.append(headRow.get(2) + " = '" + usersArches.getHeadPicture() + "'");
            sb.append(" WHERE "+headRow.get(0)+ "= " + usersArches.getUserId() + ";\n");
        }
        System.out.println(sb.toString());
        //将文本导出成文本
        FileUtil.write(ROOT + "sqlOutput.sql", sb.toString(), "UTF-8");
    }


    public static List<UsersArches> getFile() {
        File file = new File(FILE_PATH);
        InputStream is = null;
        List<UsersArches> usersArchesList = new ArrayList<>();
        try {
            is = new FileInputStream(file.getPath());
            jxl.Workbook rwb = Workbook.getWorkbook(is);
            //获取所有sheet
            Sheet[] sheets = rwb.getSheets();
            Sheet sheet = sheets[0];

            //取出表头
            Cell[] celles = sheet.getRow(0);
            headRow.add(celles[0].getContents().trim());
            headRow.add(celles[1].getContents().trim());
            headRow.add(celles[2].getContents().trim());

            //遍历第一个sheet的行
            for (int i = 1; i < sheet.getRows(); i++) {
                Cell[] row = sheet.getRow(i);
                if (row == null || row.length == 0 || row[0].getContents().trim().isEmpty()) {
                    continue;
                }
                //读取此行的内容
                UsersArches usersArches = new UsersArches();
                usersArches.setUserId(row[0].getContents().trim());
                usersArches.setNickname(row[1].getContents().trim());
                usersArches.setHeadPicture(row[2].getContents().trim());
                usersArchesList.add(usersArches);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usersArchesList;
    }

    static class UsersArches {

        private String userId;
        private String nickname;
        private String headPicture;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadPicture() {
            return headPicture;
        }

        public void setHeadPicture(String headPicture) {
            this.headPicture = headPicture;
        }
    }


}
