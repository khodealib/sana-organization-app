package com.asenadev.sana.utils;

import java.util.ArrayList;
import java.util.List;

public class MenuGeneratorUtil {


    private static final String DO_REFERRAL = "do_referral";
    private static final String MY_REFERRAL = "my_referrals";
    private static final String ARRIVAL_DEPARTURE_LIST = "arrival_departure_list";
    private static final String MY_REFERRAL_HISTORY = "my_referral_history";
    private static final String UPDATE_OWN_PROFILE = "update_own_profile";
    private static final String REFERRAL_HISTORY = "referral_history";
    private static final String IMPORT_EMPLOYEE = "import_employee";
    private static final String EMPLOYEE_LIST = "employee_list";
    private static final String MANAGE_ROLES = "manage_roles";


    public static final String DO_REFERRAL_VALUE = "ورود و خروج ارباب رجوع";
    public static final String MY_REFERRAL_VALUE = "ارباب رجوع های در انتظار";
    public static final String ARRIVAL_DEPARTURE_VALUE = "لیست ارباب رجوع";
    public static final String MY_REFERRAL_HISTORY_VALUE = "تاریخچه ارباب رجوع";
    public static final String UPDATE_OWN_PROFILE_VALUE = "صفحه شخصی";
    public static final String REFERRAL_HISTORY_VALUE = "تاریخچه کلی ارجاعات";
    public static final String IMPORT_EMPLOYEE_VALUE = "تعریف کارمندان";
    public static final String EMPLOYEE_LIST_VALUE = "مدیریت کارمندان";
    public static final String MANAGE_ROLES_VALUE = "مدیریت نقش ها";
    public static final String EXIT_VALUE = "خروج";


    public static List<String> getItemMenu(List<String> permissions) {

        List<String> menu = new ArrayList<>();
        for (String permission :
                permissions) {
            switch (permission) {

                case DO_REFERRAL:
                    menu.add(DO_REFERRAL_VALUE);
                    break;
                case MY_REFERRAL:
                    menu.add(MY_REFERRAL_VALUE);
                    break;
                case ARRIVAL_DEPARTURE_LIST:
                    menu.add(ARRIVAL_DEPARTURE_VALUE);
                    break;
                case MY_REFERRAL_HISTORY:
                    menu.add(MY_REFERRAL_HISTORY_VALUE);
                    break;
                case UPDATE_OWN_PROFILE:
                    menu.add(UPDATE_OWN_PROFILE_VALUE);
                    break;
                case REFERRAL_HISTORY:
                    menu.add(REFERRAL_HISTORY_VALUE);
                    break;
                case IMPORT_EMPLOYEE:
                    menu.add(IMPORT_EMPLOYEE_VALUE);
                    break;
                case EMPLOYEE_LIST:
                    menu.add(EMPLOYEE_LIST_VALUE);
                    break;
                case MANAGE_ROLES:
                    menu.add(MANAGE_ROLES_VALUE);
                    break;

            }
        }

        menu.add(EXIT_VALUE);

        return menu;
    }

}
