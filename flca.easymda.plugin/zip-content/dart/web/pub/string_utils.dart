
class StringUtils {

  static String trimQuotes(String value) {
    String result = value;
    if (value != null && value.startsWith("\"")) {
      result = value.trim();
      result = result.substring(1, result.length-1);
    }
    return result;
  }
 

}
