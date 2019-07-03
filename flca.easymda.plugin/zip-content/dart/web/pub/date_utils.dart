library pub.DateUtils;

import 'dart:convert';
import 'package:intl/intl.dart';

class DateUtils {

  static String dateToString(value) {
    var date = new DateTime.fromMillisecondsSinceEpoch(value);
    var formatter = new DateFormat('yyyy-MM-dd');
    String formatted = formatter.format(date);
    return formatted;
  }
 
  static DateTime varToDate(value) {
    if (value is num) {
      return new DateTime.fromMillisecondsSinceEpoch(value);
    } else {
      return DateTime.parse(value);
    }
  }

  static String dateToJson(DateTime dt) {
    return JSON.encode(dt, toEncodable: (DateTime d) => d.millisecondsSinceEpoch);
  }

}
