package betmo.com.betmo.utility;

import android.content.Context;
import android.graphics.Color;

import betmo.com.betmo.R;
import betmo.com.betmo.constant.Categories;
import betmo.com.betmo.constant.CategoryAbbrev;

/**
 * Created by kdimla on 11/22/14.
 */
public class CategoryUtility {
    public static String getAbbreviation(String category){
        if(category.equals(Categories.CAT_CATERING)){
            return CategoryAbbrev.ABBR_CATERING;
        }
        if(category.equals(Categories.CAT_CONSTRUCTION_MAT)){
            return CategoryAbbrev.ABBR_CONSTRUCTION_MATERIALS;
        }
        if(category.equals(Categories.CAT_CONSTRUCTION_PROJECTS)){
            return CategoryAbbrev.ABBR_CONSTRUCTION_PROJECTS;
        }
        if(category.equals(Categories.CAT_HARD_CONST)){
            return CategoryAbbrev.ABBR_HARD_CONST;
        }
        if(category.equals(Categories.CAT_MED_SUP)){
            return CategoryAbbrev.ABBR_MED_SUP;
        }
        if(category.equals(Categories.CAT_OFF_EQ_CONS)){
            return CategoryAbbrev.ABBR_OFF_EQ_CONS;
        }
        if(category.equals(Categories.CAT_OFFICE_EQ)){
            return CategoryAbbrev.ABBR_OFFICE_EQ;
        }
        if(category.equals(Categories.CAT_OFFICE_SUPP)){
            return CategoryAbbrev.ABBR_OFFICE_SUPP;
        }
        if(category.equals(Categories.CAT_SERVICES)){
            return CategoryAbbrev.ABBR_SERVICES;
        }
        if(category.equals(Categories.CAT_VEHICLE)){
            return CategoryAbbrev.ABBR_VEHICLE;
        }
        return CategoryAbbrev.ABBR_OTHER;
    }

    public static int getDrawable(String category){
        if(category.equals(Categories.CAT_CATERING)){
            return R.drawable.circularbg1;
        }
        if(category.equals(Categories.CAT_CONSTRUCTION_MAT)){
            return R.drawable.circularbg2;
        }
        if(category.equals(Categories.CAT_CONSTRUCTION_PROJECTS)){
            return R.drawable.circularbg3;
        }
        if(category.equals(Categories.CAT_HARD_CONST)){
            return R.drawable.circularbg4;
        }
        if(category.equals(Categories.CAT_MED_SUP)){
            return R.drawable.circularbg5;
        }
        if(category.equals(Categories.CAT_OFF_EQ_CONS)){
            return R.drawable.circularbg6;
        }
        if(category.equals(Categories.CAT_OFFICE_EQ)){
            return R.drawable.circularbg7;
        }
        if(category.equals(Categories.CAT_OFFICE_SUPP)){
            return R.drawable.circularbg8;
        }
        if(category.equals(Categories.CAT_SERVICES)){
            return R.drawable.circularbg9;
        }
        if(category.equals(Categories.CAT_VEHICLE)){
            return R.drawable.circularbg10;
        }
        return R.drawable.circularbg11;
    }

    public static int getColor(Context context, String category){
        if(category.equals(Categories.CAT_CATERING)){
            return context.getResources().getColor(R.color.betmo_color_category_1);
        }
        if(category.equals(Categories.CAT_CONSTRUCTION_MAT)){
            return context.getResources().getColor(R.color.betmo_color_category_2);
        }
        if(category.equals(Categories.CAT_CONSTRUCTION_PROJECTS)){
            return context.getResources().getColor(R.color.betmo_color_category_3);
        }
        if(category.equals(Categories.CAT_HARD_CONST)){
            return context.getResources().getColor(R.color.betmo_color_category_4);
        }
        if(category.equals(Categories.CAT_MED_SUP)){
            return context.getResources().getColor(R.color.betmo_color_category_5);
        }
        if(category.equals(Categories.CAT_OFF_EQ_CONS)){
            return context.getResources().getColor(R.color.betmo_color_category_6);
        }
        if(category.equals(Categories.CAT_OFFICE_EQ)){
            return context.getResources().getColor(R.color.betmo_color_category_7);
        }
        if(category.equals(Categories.CAT_OFFICE_SUPP)){
            return context.getResources().getColor(R.color.betmo_color_category_8);
        }
        if(category.equals(Categories.CAT_SERVICES)){
            return context.getResources().getColor(R.color.betmo_color_category_9);
        }
        if(category.equals(Categories.CAT_VEHICLE)){
            return context.getResources().getColor(R.color.betmo_color_category_10);
        }
        return context.getResources().getColor(R.color.betmo_color_category_11);
    }
}
