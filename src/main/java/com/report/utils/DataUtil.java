package com.report.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.report.auth.OauthUserDetails;
import com.report.entity.response.UserLogin;

public class DataUtil {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(DataUtil.class);

    /** The Constant fullSymbols. */
    private static final String fullSymbols = "　！“”＃＄％＆’（）＊＋，－．／：；＜＝＞？＠［￥］￡￠≒≠±∝∞" + "＾＿‘｛｜｝～゛、。※「」○◎●×△▽▲▼∵∴÷↑↓←→￣⇔⇒" + "【】『』〃…♂♀０-９ａ-ｚＡ-Ｚα-σΑ-Σ〒☆★";

    /** The Constant harfSymbols. */
    private static final String harfSymbols = " !\\\"#$%&\'()*+,\\-./:;<=>?@\\[\\\\\\]\\^_`{|}~ﾞ";

    /** The Constant YYYYMMDD_FORMAT. */
    private static final SimpleDateFormat YYYYMMDD_FORMAT = new SimpleDateFormat("ddMMyyyy");

    /** The Constant YYYY_MM_DD_FORMAT. */
    private static final SimpleDateFormat YYYY_MM_DD_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    /** The Constant YYYYMMDDHHMMSS_FORMAT. */
    private static final SimpleDateFormat YYYYMMDDHHMMSS_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    /** The Constant YYYYMMDDHHMMSS_FORMAT_STR. */
    private static final String YYYYMMDDHHMMSS_FORMAT_STR = "dd/MM/yyyy HH:mm";

    /** The Constant YYYY$MM$DD_FORMAT. */
    private static final SimpleDateFormat YYYY$MM$DD_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static final String TOKEN_PREFIX = "Bearer";

//    private static final String CSV_COMMA_DELIMITED = ";";
//    private static final String CSV_ENCODING = "UTF-8";
//    private static final String PATH_COMMA_DELIMITED = "/";
//    private static final String REFLECTION_METHOD = "get";

    /**
     * Check emailValid.
     *
     * @param email the email
     * @return true false
     */
    public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    /**
     * Check number phone 10 digit or 11 digit.
     *
     * @param number the number
     * @return true false
     */
    public static boolean checkNumberPhone(String number) {
        if (isEmpty(number)) {
            return true;
        }

        Pattern pattern = Pattern.compile("\\d{10,11}");
        return pattern.matcher(number).matches();
    }

    /**
     * Check code price.
     *
     * @param number the number
     * @return true, if successful
     */
    public static boolean checkCodePrice(String code) {
        Pattern pattern = Pattern.compile("\\d{3}");
        Matcher matcher = pattern.matcher(code);
        return matcher.matches();
    }

    /**
     * Check data 0/1.
     *
     * @param data the data
     * @return true false
     */
    public static boolean isBoolean(String data) {
        if (data.equals("0") || data.equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check zip code 7 digit.
     *
     * @param number the number
     * @return true false
     */
    public static boolean checkZipCode(String number) {
        Pattern pattern = Pattern.compile("\\d{7}");
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    /**
     * Return integer from string value.
     *
     * @param value the value
     * @return the integer
     */
    public static Integer parseIntWithNull(String value) {
        if (isEmpty(value)) {
            return null;
        } else {
            return Integer.parseInt(value);
        }
    }

    /**
     * Return float from string value.
     *
     * @param value the value
     * @return the float
     */
    public static Float parseFloatWithNull(String value) {
        if (isEmpty(value)) {
            return null;
        } else {
            return Float.parseFloat(value);
        }
    }

    /**
     * Return type JSON of String.
     *
     * @param value the value
     * @return the string
     */
    public static String convertToStringJson(String value) {
        if (isEmpty(value)) {
            return null;
        } else {
            return value;
        }
    }

    /**
     * Check if string is integer.
     *
     * @param str the str
     * @return true, if is true integer
     */
    public static boolean isTrueInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Check if string is follow pattern.
     *
     * @param value the value
     * @param patternStr the pattern str
     * @return true, false
     */
    public static boolean checkBlankOrPattern(String value, String patternStr) {
        if (isEmpty(value)) {
            return true;
        }

        final Pattern pattern = Pattern.compile(patternStr);
        try {
            return pattern.matcher(value).matches();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    /**
     * Check is empty.
     *
     * @param value the value
     * @return true, false
     */
    public static boolean isEmpty(String value) {
        if (null == value || 0 == value.length()) {
            return true;
        }

        return false;
    }

    /**
     * Check is empty.
     *
     * @param value the value
     * @return true, false
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(List value) {
        if (value == null || value.isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * Check is integer.
     *
     * @param value the value
     * @return true(integer), false(not integer)
     */
    public static boolean isInteger(String value) {
        final Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
        try {
            return pattern.matcher(value).matches();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    /**
     * Check is integer.
     *
     * @param value the value
     * @return true(integer), false(not integer)
     */
    public static boolean isIntegerWithNegative(String value) {
        final Pattern pattern = Pattern.compile("(-?)\\d+(\\.\\d+)?");
        try {
            return pattern.matcher(value).matches();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    /**
     * Check time with format HHMMSS.
     *
     * @param value the value
     * @return true, false
     */
    public static boolean isTimeHHMMSS(String value) {
        Pattern pattern = Pattern.compile("^\\d\\d[0-5]\\d[0-5]\\d$");
        try {
            return pattern.matcher(value).matches();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    /**
     * Check is a character of ASCII(not included symbols).
     *
     * @param value the value
     * @return true, false
     */
    public static boolean isASCII(String value) {
        Pattern pattern = Pattern.compile("^[\\p{Alnum}\\p{Space}]+$");
        try {
            return pattern.matcher(value).matches();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    /**
     * Check is a character of ASCII(included symbols).
     *
     * @param value the value
     * @return true, false
     */
    public static boolean isASCIISymbols(String value) {
        Pattern pattern = Pattern.compile("^[\\p{Alnum}" + harfSymbols + " ]+$");
        try {
            return pattern.matcher(value).matches();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    /**
     * Check is a character kana(excluded symbols).
     *
     * @param value the value
     * @return true, false
     */
    public static boolean isKana(String value) {
        // Pattern pattern = Pattern.compile("^[\\p{InHalfwidthandFullwidthForms}]+$");
        Pattern pattern = Pattern.compile("^(([゠-ヿ]+)|([ｦ-ﾟ]+))*$");
        try {
            return pattern.matcher(value).matches();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    /**
     * Check is a character fullsize.
     *
     * @param value the value
     * @return true, false
     */
    public static boolean isFullWidthKatakana(String value) {
        Pattern pattern = Pattern.compile("([ァ-ン]+$)");
        return pattern.matcher(value).matches();
    }

    /**
     * check is katakana fullsize and number halfsize.
     *
     * @param value the value
     * @return true, if is kata full and number
     */
    public static boolean isKataFullAndNumber(String value) {
        Pattern pattern = Pattern.compile("([ア-ン0-9]+$)");
        return pattern.matcher(value).matches();
    }

    /**
     * Check is a character kana(included symbols).
     *
     * @param value the value
     * @return true, false
     */
    public static boolean isKanaSymbols(String value) {
        Pattern pattern = Pattern.compile("^[\\p{InHalfwidthandFullwidthForms}&&[^" + fullSymbols + "]]+$");
        try {
            return pattern.matcher(value).matches();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    /**
     * Check is a character fullsize.
     *
     * @param str the str
     * @return true, false
     */
    public static boolean isZenkaku(String str) {

        boolean ret = true;
        if (str.length() < 0 || str.length() > Integer.MAX_VALUE) {
            return false;
        }

        // 一文字ずつチェックする
        for (int n = 0; n < str.length(); n++) {
            String s = str.substring(n, n + 1);
            try {
                byte[] b = s.getBytes("MS932");
                // 1バイト文字
                if (b.length == 1) {
                    // 改行コード
                    if (!(b[0] == 0x0D || b[0] == 0x0A)) {
                        ret = false;
                        continue;
                    }
                    continue;
                }

                // ２バイト文字
                if (b.length == 2) {

                    char val = (char) ((char) (b[0] & 0xFF) << 8 | (char) (b[1] & 0xFF));

                    if ( // 非漢字 0x8140～0x849E
                    !(val >= 0x8140 && val <= 0x849E) &&
                    // １３区文字 0x8740～0x879E
                            !(val >= 0x8740 && val <= 0x879E) &&
                            // 第一水準 0x889F～0x989E
                            !(val >= 0x889F && val <= 0x989E) &&
                            // 第二水準 0x989F～0xEFFC
                            !(val >= 0x989F && val <= 0xEFFC)) {
                        ret = false;
                    }
                } else {
                    ret = false;
                }
            } catch (Exception e) {
                ret = false;
            }
        }

        return ret;
    }

    /**
     * Get Json Object.
     *
     * @param strJson the str json
     * @return JsonObject
     * @throws Exception 
     * @throws ApiValidateException the api validate exception
     */
    public static JsonObject getJsonObject(String strJson) throws Exception{
        JsonObject json = null;
        try {
            if (null != strJson && 0 != strJson.length()) {
                json = new Gson().fromJson(strJson, JsonObject.class);
            }
            if (json != null && hasMember(json, "data")) {
                json = json.getAsJsonObject("data");
            }
        } catch (Exception ex) {
            json = null;
        }

        if (null == json) {
            throw new Exception("Wrong json");
        }
        return json;
    }

    /**
     * Get Json Object.
     *
     * @param json the json
     * @param memberName the member name
     * @return JsonObject
     */
    public static JsonObject getJsonObjectWithMember(JsonObject json, String memberName) {
        try {
            if (json != null && hasMember(json, memberName)) {
                json = json.getAsJsonObject(memberName);
                return json;
            }
        } catch (Exception e) {
            //
        }
        return null;
    }

    /**
     * Get Json Object.
     *
     * @param json the json
     * @param memberName the member name
     * @return JsonObject
     */
    public static JsonArray getJsonArrayWithMember(JsonObject json, String memberName) {
        JsonArray jsons;
        try {
            if (json != null && hasMember(json, memberName)) {
                jsons = json.getAsJsonArray(memberName);
                return jsons;
            }
        } catch (Exception e) {
            //
        }
        return null;
    }

    /**
     * Get Json Object.
     *
     * @param json the json
     * @param memberName the member name
     * @return JsonObject
     * @throws ApiValidateException the api validate exception
     */
    public static JsonElement getJsonJsonElement(JsonObject json, String memberName) throws Exception {
        JsonElement jsons;
        try {
            if (json != null && hasMember(json, memberName)) {
                jsons = json.get(memberName);
                return jsons;
            }
        } catch (Exception e) {
            // The JSON format is incorrect.
            throw new Exception("Wrong json");
        }
        return null;
    }

    /**
     * Get Json Object.
     *
     * @param json the json
     * @param memberName the member name
     * @return JsonObject
     * @throws ApiValidateException the api validate exception
     */
    public static String getJsonJsonElementToString(JsonObject json, String memberName) throws Exception {
        try {
            JsonElement jsons = getJsonJsonElement(json, memberName);
            if (jsons != null) {
                return jsons.toString();
            }
        } catch (Exception e) {
            // The JSON format is incorrect.
            throw new Exception("Wrong json");
        }
        return null;
    }

    /**
     * Check member is exist.
     *
     * @param json the json
     * @param memberName the member name
     * @return true, false
     */
    public static boolean has(JsonObject json, String memberName) {
        if (!json.has(memberName) || json.get(memberName).isJsonNull()) {
            return false;
        }
        return true;
    }

    /**
     * Check member is exist.
     *
     * @param json the json
     * @param memberName the member name
     * @return true, false
     */
    public static boolean hasMember(JsonObject json, String memberName) {
        return json.has(memberName);
    }

    /**
     * Get data from JSON, return String.
     *
     * @param json the json
     * @param memberName the member name
     * @return String
     */
    public static String getJsonString(JsonObject json, String memberName) {
        return getJsonString(json, memberName, null);
    }

    /**
     * Get data from JSON, return String.
     *
     * @param json the json
     * @param memberName the member name
     * @param defaultValue the default value
     * @return String
     */
    public static String getJsonString(JsonObject json, String memberName, String defaultValue) {
        if (!json.has(memberName) || json.get(memberName).isJsonNull()) {
            return defaultValue;
        }
        return json.get(memberName).getAsString().trim();
    }

    /**
     * Get data to JSON, return Integer.
     *
     * @param json the json
     * @param memberName the member name
     * @return Integer
     */
    public static Integer getJsonInteger(JsonObject json, String memberName) {
        return getJsonInteger(json, memberName, null);
    }

    /**
     * Get data to JSON, return Integer.
     *
     * @param json the json
     * @param memberName the member name
     * @param defaultValue the default value
     * @return Integer
     */
    public static Integer getJsonInteger(JsonObject json, String memberName, Integer defaultValue) {
        if (!json.has(memberName) || json.get(memberName).isJsonNull()) {
            return defaultValue;
        }
        try {
            return json.get(memberName).getAsInt();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Get map data from JSON String.
     *
     * @param json the json
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> createJsonMap(String json) {
        return (Map<String, Object>) new Gson().fromJson(json, Map.class);
    }

    /**
     * Get map data of member from JSON String.
     *
     * @param json the json
     * @param memberName the member name
     * @return Map<String, Object>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getJsonMap(Map<String, Object> json, String memberName) {
        return (Map<String, Object>) json.get(memberName);
    }

    /**
     * Get data from map JSON, return String.
     *
     * @param json the json
     * @param memberName the member name
     * @return String
     */
    public static String getJsonString(Map<String, Object> json, String memberName) {
        return getJsonString(json, memberName, null);
    }

    /**
     * Get data from map JSON, return String.
     *
     * @param json the json
     * @param memberName the member name
     * @param defaultValue the default value
     * @return String
     */
    public static String getJsonString(Map<String, Object> json, String memberName, String defaultValue) {
        Object value = json.get(memberName);
        if (value == null) {
            return defaultValue;
        } else if (value instanceof String) {
            return (String) value;
        } else if (value instanceof Number) {
            // Number型ならintの文字列として返す
            Number num = (Number) json.get(memberName);
            return String.valueOf(num.intValue());
        } else {
            return value.toString();
        }
    }

    /**
     * Get data from map JSON, return Integer.
     *
     * @param json the json
     * @param memberName the member name
     * @return Integer
     */
    public static Integer getJsonInteger(Map<String, Object> json, String memberName) {
        return getJsonInteger(json, memberName, null);
    }

    /**
     * Get data from map JSON, return Integer.
     *
     * @param json the json
     * @param memberName the member name
     * @param defaultValue the default value
     * @return Integer
     */
    public static Integer getJsonInteger(Map<String, Object> json, String memberName, Integer defaultValue) {
        Object value = json.get(memberName);
        if (value == null) {
            return defaultValue;
        } else if (value instanceof String) {
            String str = (String) value;
            if (str.length() == 0) {
                return defaultValue;
            } else {
                return Integer.valueOf(str);
            }
        } else {
            Number num = (Number) json.get(memberName);
            if (num instanceof Integer) {
                return (Integer) num;
            } else {
                return num.intValue();
            }
        }
    }

    /**
     * Convert Date to String.
     *
     * @param date the date
     * @return String
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return null;
        }

        try {
            return YYYYMMDD_FORMAT.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Convert Date to String.
     *
     * @param date the date
     * @return String
     */
    public static String dateToStringYYYYMMDDHHMMSS(Date date) {
        if (date == null) {
            return null;
        }

        try {
            return YYYYMMDDHHMMSS_FORMAT.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Convert Date to String.
     *
     * @param date the date
     * @return String
     */
    public static String dateToString(Date date, String patttern) {
        if (date == null) {
            return null;
        }

        try {
            SimpleDateFormat format = new SimpleDateFormat(patttern);
            return format.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Convert Date to String.
     *
     * @return String
     */
    public static String getLocalDateTime() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYYMMDDHHMMSS_FORMAT_STR);
            return LocalDateTime.now().format(formatter);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * Convert Date to String.
     *
     * @return String
     */
    public static String getDateTime() {
        try {
            LocalDateTime localTime = LocalDateTime.now();
            int year = localTime.getYear();
            String month = "" + localTime.getMonthValue();
            String day = "" + localTime.getDayOfMonth();
            String hours = "" + localTime.getHour();
            String minute = "" + localTime.getMinute();
            String seconds = "" + localTime.getSecond();
            if (localTime.getMonthValue() < 10) {
                month = "0" + localTime.getMonthValue();
            }
            if (localTime.getDayOfMonth() < 10) {
                day = "0" + localTime.getDayOfMonth();
            }
            if (localTime.getHour() < 10) {
                hours = "0" + localTime.getHour();
            }
            if (localTime.getMinute() < 10) {
                minute = "0" + localTime.getMinute();
            }
            if (localTime.getSecond() < 10) {
                seconds = "0" + localTime.getSecond();
            }
            return year + "" + month + "" + day + "" + hours + "" + minute + "" + seconds;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * Convert Date to String.
     *
     * @param date the date
     * @return String
     */
    public static String dateToStringYYYY_MM_DD(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return YYYY_MM_DD_FORMAT.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Convert Date to String.
     *
     * @param date the date
     * @return String
     */
    public static String dateToStringYYYY$MM$DD(Date date) {
        if (date == null) {
            return null;
        }
        try {
            return YYYY$MM$DD_FORMAT.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * convert string to date.
     *
     * @param dateString the date string
     * @return the date
     */
    public static Date convertStringToDate(String dateString) {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = originalFormat.parse(dateString);
        } catch (Exception ex) {
            return null;
        }
        return date;
    }

    /**
     * convert string to date.
     *
     * @param dateString the date string
     * @return the date
     */
    public static Date convertStringToDate(String dateString, String patten) {
        SimpleDateFormat originalFormat = new SimpleDateFormat(patten);
        Date date = null;
        try {
            date = originalFormat.parse(dateString);
        } catch (Exception ex) {
            return null;
        }
        return date;
    }

    /**
     * check input value.
     *
     * @param value the value
     * @return true, if successful
     */
    public static boolean checkValue(String value) {
        final Pattern pattern = Pattern.compile("^[1-9]");
        try {
            return pattern.matcher(value).matches();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    /**
     * Get data to JSON, return Double.
     *
     * @param json the json
     * @param memberName the member name
     * @return Integer
     */
    public static Double getJsonDouble(JsonObject json, String memberName) {
        return getJsonDouble(json, memberName, null);
    }

    /**
     * Get data to JSON, return Double.
     *
     * @param json the json
     * @param memberName the member name
     * @param defaultValue the default value
     * @return Integer
     */
    public static Double getJsonDouble(JsonObject json, String memberName, Double defaultValue) {
        if (!json.has(memberName) || json.get(memberName).isJsonNull()) {
            return defaultValue;
        }
        try {
            return json.get(memberName).getAsDouble();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Checks if is zero one.
     *
     * @param value the value
     * @return true, if is zero one
     */
    public static boolean isZeroOne(String value) {
        final Pattern pattern = Pattern.compile("^[0-1]");
        try {
            return pattern.matcher(value).matches();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    /**
     * Checks if is zero to three.
     *
     * @param value the value
     * @return true, if is zero to three
     */
    public static boolean isZeroToThree(String value) {
        final Pattern pattern = Pattern.compile("^[0-3]");
        try {
            return pattern.matcher(value).matches();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    /**
     * Checks if is zero to two.
     *
     * @param value the value
     * @return true, if is zero to two
     */
    public static boolean isZeroToTwo(String value) {
        final Pattern pattern = Pattern.compile("^[0-2]");
        try {
            return pattern.matcher(value).matches();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    /**
     * Convert string to timestamp.
     *
     * @param strTimestamp the str timestamp
     * @return the timestamp
     */
    public static Timestamp convertStringToTimestamp(String strTimestamp) {
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = (Date) formatter.parse(strTimestamp);
            Timestamp timeStampDate = new Timestamp(date.getTime());
            return timeStampDate;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Checks if is phone number.
     *
     * @param tel the tel
     * @return true, if is phone number
     */
    public static boolean isPhoneNumber(String tel) {
        Pattern pattern = Pattern.compile("^[0-9]{1,13}$");
        return pattern.matcher(tel).matches();
    }

    /**
     * Checks if is phone number start zero.
     *
     * @param tel the tel
     * @return true, if is phone number start zero
     */
    public static boolean isPhoneNumberStartZero(String tel) {
        Pattern pattern = Pattern.compile("^0\\d*$");
        return pattern.matcher(tel).matches();
    }

    /**
     * deleteListFileInfolder.
     *
     * @param source the source
     * @return boolean
     */
    public static boolean deleteListFileInfolder(File source) {
        if (source.exists()) {
            File[] listFile = source.listFiles();
            if (listFile.length != 0) {
                for (File f : listFile) {
                    if (f.isFile()) {
                        f.delete();
                    }
                }
            }
            source.delete();
            return true;
        }
        return false;
    }

    /**
     * Trim all size.
     *
     * @param string the string
     * @return the string
     */
    public static String trimAllSize(String string) {
        String trim;
        trim = string.replaceFirst("(　| )+$", "");
        return trim.replaceFirst("^(　| )+", "");
    }

    /**
     * Trim all space character in begin and end of the string, if input string is
     * null then return null.
     *
     * @author (FPT)QuanKK
     * @param string the string
     * @return the string
     */
    public static String trimAllSizeWithNull(String string) {
        if (string != null) {
            String trim;
            trim = string.replaceFirst("(　| )+$", "");
            return trim.replaceFirst("^(　| )+", "");
        } else {
            return null;
        }
    }

    /**
     * Check numbers.
     *
     * @param value the value
     * @return true(integer), false(not integer)
     */
    public static boolean isNumeric(String value) {
        final Pattern pattern = Pattern.compile("[+-]?\\d*(\\.\\d+)?");
        try {
            return pattern.matcher(value).matches();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }

    /**
     * Convert object to json string.
     *
     * @param data the data
     * @return String
     */
    public static String convertObjectToJsonString(Object data) {

        Gson gson = new Gson();
        return gson.toJson(data);
    }

    /**
     * Convert object to json string with NULL.
     *
     * @param data the data
     * @return the string
     */
    public static String convertObjectToJsonStringWithNull(Object data) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        return gson.toJson(data);
    }

    /**
     * Change path.
     *
     * @param path the path
     * @return the string
     */
    /*
     * パスを更新する
     */
    public static String changePath(String path) {
        if (path.startsWith("\"")) {
            path = path.substring(1);
        }

        if (path.endsWith("\"")) {
            path = path.substring(0, path.length() - 1);
        }

        if (!path.endsWith("/")) {
            path += "/";
        }
        return path;
    }

    /**
     * セッションのタイムアウトをチェックする.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return true, if is expired
     */
    public static boolean isExpired(Date date1, Date date2) {
        return date1 != null && date2 != null && date1.before(date2);
    }

    /**
     * Get token id from token or refresh token.
     *
     * @param value the value
     * @return token id
     */
    public static String extractTokenKey(String value) {
        if (value == null) {
            return null;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        }

        try {
            byte[] bytes = digest.digest(value.getBytes("UTF-8"));
            return String.format("%032x", new BigInteger(1, bytes));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
        }
    }

    /**
     * Gets the json big decimal.
     *
     * @param json the json
     * @param memberName the member name
     * @return the json big decimal
     * @throws ApiValidateException the api validate exception
     */
    public static BigDecimal getJsonBigDecimal(JsonObject json, String memberName) throws Exception {
        if (!json.has(memberName) || json.get(memberName).isJsonNull()) {
            return null;
        }
        try {
            return json.get(memberName).getAsBigDecimal();
        } catch (Exception e) {
            throw new Exception("BigDecimal format no correct!");
        }
    }

    public static Date getJsonDate(JsonObject json, String memberName) throws Exception {
        if (!json.has(memberName) || json.get(memberName).isJsonNull()) {
            return null;
        }
        try {
            return YYYY_MM_DD_FORMAT.parse(json.get(memberName).getAsString());
        } catch (Exception e) {
            throw new Exception("Date format no correct!");
        }
    }

    public static UserLogin getUserInfoByToken(HttpServletRequest request) {
        OAuth2Authentication auth2Authentication = (OAuth2Authentication) request.getUserPrincipal();
        UsernamePasswordAuthenticationToken oAuthUserDetails = (UsernamePasswordAuthenticationToken) auth2Authentication.getUserAuthentication();
        OauthUserDetails userDetails = (OauthUserDetails) oAuthUserDetails.getPrincipal();
        return userDetails.getUser();
    }

    public static String getUserNameByToken(HttpServletRequest request) {
        OAuth2Authentication auth2Authentication = (OAuth2Authentication) request.getUserPrincipal();
        UsernamePasswordAuthenticationToken oAuthUserDetails = (UsernamePasswordAuthenticationToken) auth2Authentication.getUserAuthentication();
        OauthUserDetails userDetails = (OauthUserDetails) oAuthUserDetails.getPrincipal();
        return userDetails.getUsername();
    }

    public static Integer getUserIdByToken(HttpServletRequest request) {
        OAuth2Authentication auth2Authentication = (OAuth2Authentication) request.getUserPrincipal();
        UsernamePasswordAuthenticationToken oAuthUserDetails = (UsernamePasswordAuthenticationToken) auth2Authentication.getUserAuthentication();
        OauthUserDetails userDetails = (OauthUserDetails) oAuthUserDetails.getPrincipal();
        return userDetails.getUser().getUserId();
    }
//
//    /**
//     * Get user id from token.
//     *
//     * @param request the request
//     * @return the user id by token
//     */
//    public static Integer getCompanyIdByToken(HttpServletRequest request) {
//        OAuth2Authentication auth2Authentication = (OAuth2Authentication) request.getUserPrincipal();
//        UsernamePasswordAuthenticationToken oAuthUserDetails = (UsernamePasswordAuthenticationToken) auth2Authentication.getUserAuthentication();
//        OAuthUserDetails userDetails = (OAuthUserDetails) oAuthUserDetails.getPrincipal();
//        return userDetails.getUser().getCompanyId();
//    }
//
//    /**
//     * Get user id from token.
//     *
//     * @param request the request
//     * @return the user id by token
//     */
//    public static Integer getCompanyIdByToken() {
//        OAuth2Authentication auth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
//        UsernamePasswordAuthenticationToken oAuthUserDetails = (UsernamePasswordAuthenticationToken) auth2Authentication.getUserAuthentication();
//        OAuthUserDetails userDetails = (OAuthUserDetails) oAuthUserDetails.getPrincipal();
//        return userDetails.getUser().getCompanyId();
//    }
//
//    public static void isUserOfCompany(Integer companyId) throws ApiValidateException {
//        Integer companydIdFromToken = getCompanyIdByToken();
//        if (Objects.isNull(companyId) || !companydIdFromToken.equals(companyId)) {
//            throw new ApiValidateException(Constants.ID_BKE00024, MessageUtils.getMessage(Constants.ID_BKE00024));
//        }
//    }
//
//    /**
//     * Get user name from token.
//     *
//     * @param request the request
//     * @return the user id by token
//     */
//    public static String getUserNameByToken() {
//        OAuth2Authentication auth2Authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
//        UsernamePasswordAuthenticationToken oAuthUserDetails = (UsernamePasswordAuthenticationToken) auth2Authentication.getUserAuthentication();
//        OAuthUserDetails userDetails = (OAuthUserDetails) oAuthUserDetails.getPrincipal();
//        return userDetails.getUsername();
//    }
//
//    /**
//     * Gen password.
//     *
//     * @return the string
//     */
//    public static String genPassword() {
//        try {
//            Generex generex = new Generex("([0-9a-zA-Z]{8})");
//            return generex.random();
//        } catch (Error e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    public static boolean checkMinLength(String value, int minlength) {
//        if (DataUtil.isEmpty(value) || value.length() < minlength) {
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean checkMaxLength(String value, int maxlength) {
//        if (DataUtil.isEmpty(value) || value.length() > maxlength) {
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean isNumber(String value) {
//        if (DataUtil.isEmpty(value)) {
//            return true;
//        }
//        Pattern pattern = Pattern.compile("\\d+");
//        Matcher matcher = pattern.matcher(value);
//        return matcher.matches();
//    }
//
//    public static boolean checkPattern(String value, String sPattern) {
//        if (DataUtil.isEmpty(value)) {
//            return true;
//        }
//        Pattern pattern = Pattern.compile(sPattern);
//        Matcher matcher = pattern.matcher(value);
//        return matcher.matches();
//    }
//
//    public static boolean isNull(JsonObject json, String memberName) {
//        if (!json.has(memberName) || json.get(memberName).isJsonNull()) {
//            return true;
//        }
//        return false;
//    }
//
//    public static boolean isDate(String value, String sPattern) {
//        if (isEmpty(value)) {
//            return true;
//        }
//        SimpleDateFormat format = new SimpleDateFormat(sPattern);
//        format.setLenient(false);
//        try {
//            format.parse(value);
//        } catch (ParseException ex) {
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean isPositiveNumber(Integer value) {
//        if (value != null && value > 0) {
//            return true;
//        }
//        return false;
//    }
//
    public static String getAccessToken(HttpServletRequest httpServletRequest) {
        return getAccessToken(httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION));
    }

    /**
     *
     * @param header
     * @return
     */
    public static String getAccessToken(String header) {
        if (isEmpty(header)) {
            return null;
        }
        final int space = header.indexOf(' ');
        if (space <= 0) {
            return null;
        }
        final String method = header.substring(0, space);
        if (!TOKEN_PREFIX.equalsIgnoreCase(method)) {
            return null;
        }
        return header.substring(space + 1);
    }
//
//    public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
//        String mineType = servletContext.getMimeType(fileName);
//        try {
//            MediaType mediaType = MediaType.parseMediaType(mineType);
//            return mediaType;
//        } catch (Exception e) {
//            return MediaType.APPLICATION_OCTET_STREAM;
//        }
//    }
//
//    public static void createCSVFromObjectList(List<?> objectList, String filePath)
//            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
//        if (objectList.size() < 1) {
//            return;
//        }
//        createDirectToryIfNotExist(filePath.substring(0, filePath.lastIndexOf(PATH_COMMA_DELIMITED) + 1));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), CSV_ENCODING));
//
//        Class<? extends Object> cls = objectList.get(0).getClass();
//        Field fieldlist[] = cls.getDeclaredFields();
//        StringBuilder builder = new StringBuilder();
//        Annotation[] annotations;
//        String fieldName;
//        for (Field field : fieldlist) {
//            if (builder.toString().length() > 0) {
//                builder.append(CSV_COMMA_DELIMITED);
//            }
//            fieldName = field.getName();
//            annotations = field.getDeclaredAnnotations();
//            for (Annotation ano : annotations) {
//                if (ano instanceof JsonProperty) {
//                    fieldName = ((JsonProperty) ano).value();
//                }
//            }
//            builder.append(fieldName);
//        }
//        bw.write(builder.toString());
//        Method method;
//
//        Iterator<?> it = objectList.iterator();
//        Object object;
//        while (it.hasNext()) {
//            object = it.next();
//            bw.newLine();
//            builder = new StringBuilder();
//            for (Field field : fieldlist) {
//                method = cls.getMethod(
//                        REFLECTION_METHOD + field.getName().replaceFirst(field.getName().substring(0, 1), field.getName().substring(0, 1).toUpperCase()));
//                if (builder.toString().length() > 0) {
//                    builder.append(CSV_COMMA_DELIMITED);
//                }
//                builder.append(String.valueOf(method.invoke(object)));
//            }
//            bw.write(builder.toString());
//        }
//        bw.flush();
//        bw.close();
//    }
//
//    public static void createDirectToryIfNotExist(String path) {
//        File directory = new File(path);
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//    }

}
