package edu.nf.student.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ʱ��
 * @author admin
 *
 */
public class DateUtils {
       public static Date getDate(String data) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//���Է�����޸����ڸ�ʽ
			try {
				return dateFormat.parse(data);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}	
       }
      
}
