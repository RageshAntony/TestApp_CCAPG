/*      */ package mumbai.dev.sdkdubai;
/*      */ 
/*      */ import android.annotation.SuppressLint;
/*      */ import android.app.AlertDialog;
/*      */ import android.content.Context;
/*      */ import android.content.DialogInterface;
/*      */ import android.content.Intent;
/*      */ import android.content.res.ColorStateList;
/*      */ import android.graphics.Color;
/*      */ import android.graphics.PorterDuff;
/*      */ import android.graphics.drawable.Drawable;
/*      */ import android.graphics.drawable.GradientDrawable;
/*      */ import android.os.Build;
/*      */ import android.os.Bundle;
import android.util.Log;
/*      */ import android.view.MenuItem;
/*      */ import android.view.View;
/*      */ import android.view.ViewGroup;
/*      */ import android.view.inputmethod.InputMethodManager;
/*      */ import android.widget.AdapterView;
/*      */ import android.widget.ArrayAdapter;
/*      */ import android.widget.Button;
/*      */ import android.widget.CheckBox;
/*      */ import android.widget.CompoundButton;
/*      */ import android.widget.EditText;
/*      */ import android.widget.ImageView;
/*      */ import android.widget.LinearLayout;
/*      */ import android.widget.ScrollView;
/*      */ import android.widget.Spinner;
/*      */ import android.widget.SpinnerAdapter;
/*      */ import android.widget.TextView;
/*      */ import android.widget.Toast;
/*      */ import androidx.annotation.ColorInt;
/*      */ import androidx.appcompat.app.AppCompatActivity;
/*      */ import androidx.appcompat.widget.AppCompatEditText;
/*      */ import androidx.appcompat.widget.AppCompatSpinner;
/*      */ import androidx.appcompat.widget.Toolbar;
/*      */ import androidx.core.content.ContextCompat;
/*      */ import androidx.core.view.ViewCompat;
/*      */ import androidx.core.widget.CompoundButtonCompat;
/*      */ import androidx.viewpager.widget.ViewPager;
/*      */ import com.android.volley.Request;
/*      */ import com.android.volley.RequestQueue;
/*      */// import com.android.volley.Response;
/*      */ import com.android.volley.VolleyError;
/*      */ import com.android.volley.toolbox.StringRequest;
/*      */ import com.android.volley.toolbox.Volley;
/*      */ import com.google.android.material.textfield.TextInputLayout;
/*      */ import java.lang.reflect.Field;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import mumbai.dev.sdkdubai.adapter.MovieAdapter;
/*      */ import mumbai.dev.sdkdubai.dto.CardTypeDTO;
/*      */ import mumbai.dev.sdkdubai.dto.PaymentOptionDTO;
/*      */ import mumbai.dev.sdkdubai.dto.PaymentType;
/*      */ import mumbai.dev.sdkdubai.dto.Promotion;
/*      */ import mumbai.dev.sdkdubai.dto.SaveCard;
/*      */ import net.cachapa.expandablelayout.ExpandableLayout;
/*      */ import org.json.JSONArray;
/*      */ import org.json.JSONException;
/*      */ import org.json.JSONObject;
/*      */ import retrofit2.Call;
/*      */ import retrofit2.Callback;
/*      */ import retrofit2.Response;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PaymentOptions
/*      */   extends AppCompatActivity
/*      */   implements View.OnClickListener, onAddTextViewCustomListener
/*      */ {
/*   80 */   String color_text = "";
/*   81 */   String color_btn = "";
/*   82 */   String color_toolbar = "";
/*   83 */   String color_grad_min = "";
/*   84 */   String headerDivider = "";
/*   85 */   String headerTopBkg = "";
/*   86 */   String headerTxt = "";
/*   87 */   String buttonTxt = "";
/*   88 */   String bodyTxt = "";
/*   89 */   String inactiveTabTxt = "";
/*   90 */   String inactiveTabBkg = "";
/*      */   static MerchantDetails merchant;
/*      */   static BillingAddress billing;
/*      */   static ShippingAddress shipping;
/*      */   static StandardInstructions standardInstructions;
/*      */   boolean flg_edit = false;
/*   96 */   String selected_billing = "billing";
/*      */   ViewPager viewPager;
/*      */   ViewPagerAdapter adapter;
/*      */   Context mContext;
/*      */   MovieAdapter movieAdapter;
/*  101 */   List<PaymentType> movieArrayList = new ArrayList<>(); TextView tv_inr; TextView tv_orderid; TextView txt_billing; TextView txt_shipping;
/*      */   TextView txt_promo;
/*      */   Toolbar mToolbar;
/*  104 */   List<SaveCard> SavedCardList = new ArrayList<>();
/*      */   JSONObject jsonRespObj;
/*      */   Spinner payment_type;
/*  107 */   Map<String, ArrayList<CardTypeDTO>> cardsList = new LinkedHashMap<>();
/*  108 */   ArrayList<PaymentOptionDTO> payOptionList = new ArrayList<>();
/*      */   View header_divider;
/*  110 */   static ArrayList<Promotion> promolist = new ArrayList<>();
/*  111 */   static int pos = 0;
/*  112 */   static int pos_page = 0;
/*  113 */   final List<String> list_spinner = new ArrayList<>();
/*  114 */   final List<String> list_spinner_code = new ArrayList<>(); String selectedPaymentOption; CardTypeDTO selectedCardType; TextView payother; TextView savedcard; TextView billing_name; TextView billing_add1; TextView billing_add2; TextView billing_mob; TextView billing_email; TextView edit; TextView billing_edit_head; TextView promo_txt; TextView coupon_txt; TextView mkpay; TextView mkpay1; LinearLayout ll_main_edit; LinearLayout ll_main; LinearLayout ll_main_billing; LinearLayout ll_saved_card;
/*      */   LinearLayout ll_new_card;
/*      */   LinearLayout ll_promo;
/*  117 */   ArrayList<String> cvv_list = new ArrayList<>(); LinearLayout ll_billing_address; LinearLayout ll_coupon; EditText name_edit; EditText address_edit; EditText city_edit; EditText state_edit; EditText country_edit; EditText phonenumber_edit; EditText email_edit; EditText edt_coupon; TextInputLayout edit_email;
/*      */   Button pay_card;
/*      */   Button apply;
/*      */   ImageView expand;
/*      */   private EditText cardNumber;
/*      */   private EditText cardCvv;
/*      */   private EditText expiryMonth;
/*      */   private EditText expiryYear;
/*      */   private EditText issuingBank;
/*  126 */   private Map<String, String> paymentOptions = new LinkedHashMap<>(); private ExpandableLayout expandableLayout0;
/*      */   EditText ip_exp;
/*      */   EditText ip_cvv;
/*  129 */   String cardname = "Unknown"; CreditCardEditText creditcard; CheckBox checkBox1;
/*      */   AppCompatSpinner promolist_spinner;
/*      */   LinearLayout btm;
/*      */   LinearLayout head;
/*      */   LinearLayout head_edit;
/*      */   ScrollView scrl;
/*      */   String amt;
/*      */   
/*      */   protected void onCreate(Bundle savedInstanceState) {
/*  138 */     super.onCreate(savedInstanceState);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  144 */     setContentView(R.layout.activity_payment_options);
/*  145 */     this.mContext = (Context)this;
/*  146 */     pos = 0;
/*  147 */     pos_page = 0;
/*  148 */     Intent intent = getIntent();
/*  149 */     merchant = (MerchantDetails)intent.getParcelableExtra("merchant");
/*  150 */     billing = (BillingAddress)intent.getParcelableExtra("billing");
/*  151 */     shipping = (ShippingAddress)intent.getParcelableExtra("shipping");
/*  152 */     standardInstructions = (StandardInstructions)intent.getParcelableExtra("standard instructions");
/*      */     
/*  154 */     this.mToolbar = (Toolbar)findViewById(R.id.toolbar);
/*  155 */     this.btm = (LinearLayout)findViewById(R.id.btm);
/*  156 */     this.scrl = (ScrollView)findViewById(R.id.scrl);
/*  157 */     this.mToolbar.setVisibility(8);
/*  158 */     this.btm.setVisibility(8);
/*  159 */     this.scrl.setVisibility(8);
/*      */     
/*  161 */     boolean chk = validate_merchant();
/*  162 */     if (chk) {
/*  163 */       LoadingDialog.showLoadingDialog(this.mContext, "Loading...");
/*  164 */       getcolor();
/*      */     } else {
/*  166 */       finish();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void after_hit() {
/*  209 */     LoadingDialog.cancelLoading();
/*      */     
/*  211 */     boolean flg = false;
/*  212 */     if (merchant.getPromo_code().length() > 0) {
/*      */       int i;
/*  214 */       for (i = 0; i < this.list_spinner_code.size(); i++) {
/*  215 */         if (((String)this.list_spinner_code.get(i)).equalsIgnoreCase(merchant.getPromo_code())) {
/*  216 */           flg = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/*      */       
/*  222 */       if (flg) {
/*      */ 
/*      */ 
/*      */         
/*  226 */         this.selected_billing = "promo";
/*      */         
/*  228 */         this.ll_billing_address.setVisibility(8);
/*  229 */         this.ll_promo.setVisibility(0);
/*  230 */         this.billing_name.setText(shipping.getName());
/*  231 */         this.billing_add1.setText(shipping.getAddress());
/*  232 */         this.billing_add2.setText(shipping.getCity() + " " + shipping.getState() + " " + shipping.getCountry());
/*  233 */         this.billing_mob.setText(shipping.getTelephone());
/*  234 */         this.billing_email.setVisibility(8);
/*  235 */         this.txt_billing.setVisibility(0);
/*  236 */         this.txt_shipping.setVisibility(0);
/*  237 */         this.txt_promo.setTextColor(Color.parseColor(this.color_text));
/*  238 */         this.txt_promo.setBackgroundColor(getResources().getColor(R.color.white));
/*      */         
/*  240 */         this.txt_billing.setTextColor(Color.parseColor(this.inactiveTabTxt));
/*  241 */         this.txt_billing.setBackgroundColor(Color.parseColor(this.inactiveTabBkg));
/*      */         
/*  243 */         this.txt_shipping.setTextColor(Color.parseColor(this.inactiveTabTxt));
/*  244 */         this.txt_shipping.setBackgroundColor(Color.parseColor(this.inactiveTabBkg));
/*      */ 
/*      */         
/*  247 */         this.promolist_spinner.setSelection(i);
/*      */       } else {
/*  249 */         showToast("Invalid Promo code !!!");
/*  250 */         finish();
/*      */       } 
/*      */     } 
/*      */     
/*  254 */     this.mToolbar.setVisibility(0);
/*  255 */     this.btm.setVisibility(0);
/*  256 */     this.scrl.setVisibility(0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onClick(View v) {
/*  262 */     int id = v.getId();
/*  263 */     if (id == R.id.expand) {
/*  264 */       if (this.expandableLayout0.isExpanded()) {
/*      */         
/*  266 */         this.expandableLayout0.collapse();
/*      */       } else {
/*      */         
/*  269 */         this.expandableLayout0.expand();
/*      */       }
/*      */     
/*  272 */     } else if (id == R.id.apply) {
/*  273 */       if (this.apply.getText().toString().equalsIgnoreCase("Apply"))
/*  274 */       { if (this.edt_coupon.length() > 0) {
/*  275 */           apply_coupon();
/*      */         } else {
/*  277 */           showToast("Please enter valid coupon code");
/*      */         }  }
/*  279 */       else { remove_coupon(); }
/*      */     
/*  281 */     } else if (id == R.id.pay_card) {
/*  282 */       if (this.pay_card.getText().toString().equalsIgnoreCase("UPDATE")) {
/*  283 */         this.ll_main.setVisibility(0);
/*  284 */         this.ll_main_edit.setVisibility(8);
/*      */ 
/*      */         
/*  287 */         if (this.selected_billing.equalsIgnoreCase("billing")) {
/*      */           
/*  289 */           billing.setName(this.name_edit.getText().toString().trim());
/*  290 */           billing.setAddress(this.address_edit.getText().toString().trim());
/*  291 */           billing.setCountry(this.country_edit.getText().toString().trim());
/*  292 */           billing.setState(this.state_edit.getText().toString().trim());
/*  293 */           billing.setCity(this.city_edit.getText().toString().trim());
/*  294 */           billing.setTelephone(this.phonenumber_edit.getText().toString().trim());
/*  295 */           billing.setEmail(this.email_edit.getText().toString().trim());
/*      */ 
/*      */           
/*  298 */           this.billing_name.setText(billing.getName());
/*  299 */           this.billing_add1.setText(billing.getAddress());
/*  300 */           this.billing_add2.setText(billing.getCity() + " " + billing.getState() + " " + billing.getCountry());
/*  301 */           this.billing_mob.setText(billing.getTelephone());
/*  302 */           this.edit_email.setVisibility(0);
/*  303 */           this.billing_email.setText(billing.getEmail());
/*      */           
/*  305 */           if (this.promo_txt.getText().toString().equalsIgnoreCase("Promotion applied")) {
/*  306 */             this.pay_card.setText("PAY " + this.amt + " " + merchant.currency);
/*      */           } else {
/*  308 */             this.pay_card.setText("PAY " + merchant.amount + " " + merchant.currency);
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/*  313 */           shipping.setName(this.name_edit.getText().toString().trim());
/*  314 */           shipping.setAddress(this.address_edit.getText().toString().trim());
/*  315 */           shipping.setCountry(this.country_edit.getText().toString().trim());
/*  316 */           shipping.setState(this.state_edit.getText().toString().trim());
/*  317 */           shipping.setCity(this.city_edit.getText().toString().trim());
/*  318 */           shipping.setTelephone(this.phonenumber_edit.getText().toString().trim());
/*      */ 
/*      */           
/*  321 */           this.billing_name.setText(shipping.getName());
/*  322 */           this.billing_add1.setText(shipping.getAddress());
/*  323 */           this.billing_add2.setText(shipping.getCity() + " " + shipping.getState() + " " + shipping.getCountry());
/*  324 */           this.billing_mob.setText(shipping.getTelephone());
/*  325 */           this.edit_email.setVisibility(8);
/*      */           
/*  327 */           if (this.promo_txt.getText().toString().equalsIgnoreCase("Promotion applied")) {
/*  328 */             this.pay_card.setText("PAY " + this.amt + " " + merchant.currency);
/*      */           } else {
/*  330 */             this.pay_card.setText("PAY " + merchant.amount + " " + merchant.currency);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  335 */         InputMethodManager imm = (InputMethodManager)getSystemService("input_method");
/*  336 */         imm.toggleSoftInput(1, 0);
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  343 */         boolean chk_b = true;
/*  344 */         boolean chk_s = true;
/*      */         
/*  346 */         if (Constants.BILLING.equalsIgnoreCase("Y")) {
/*  347 */           chk_b = validate_billing();
/*      */         }
/*      */         
/*  350 */         if (Constants.SHIPPING.equalsIgnoreCase("Y")) {
/*  351 */           chk_s = validate_shipping();
/*      */         }
/*      */ 
/*      */         
/*  355 */         if (chk_b && chk_s) {
/*  356 */           if (this.ll_new_card.getVisibility() == 0) {
/*      */             
/*  358 */             if (this.creditcard.getText().toString().length() < 15) {
/*  359 */               showToast("Please enter valid credit card number");
/*  360 */             } else if (!Check(this.creditcard.getText().toString())) {
/*  361 */               showToast("Please enter valid credit card number");
/*  362 */             } else if (this.ip_exp.getText().toString().length() != 5) {
/*  363 */               showToast("Please enter valid expiry date");
/*  364 */             } else if (this.ip_cvv.getText().toString().length() != 3) {
/*  365 */               showToast("Please enter valid CVV");
/*  366 */             } else if (this.promo_success.toString().equalsIgnoreCase("no")) {
/*  367 */               showToast("Please use another card for selected promotion");
/*      */             }
/*      */             else {
/*      */               
/*  371 */               pay_new();
/*      */ 
/*      */             
/*      */             }
/*      */ 
/*      */           
/*      */           }
/*  378 */           else if (((String)this.cvv_list.get(this.viewPager.getCurrentItem())).length() < 3) {
/*  379 */             showToast("Please enter valid CVV");
/*  380 */           } else if (this.promo_success.toString().equalsIgnoreCase("no")) {
/*  381 */             showToast("Please use another card for selected promotion");
/*      */           } else {
/*  383 */             pay_saved();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  453 */     else if (id == R.id.payother) {
/*  454 */       remove_promo();
/*  455 */       this.creditcard.setText("");
/*  456 */       this.ip_cvv.setText("");
/*  457 */       this.ip_exp.setText("");
/*      */       
/*  459 */       this.ll_saved_card.setVisibility(8);
/*  460 */       this.ll_new_card.setVisibility(0);
/*      */     }
/*  462 */     else if (id == R.id.savedcard) {
/*  463 */       remove_promo();
/*      */ 
/*      */       
/*  466 */       for (int i = 0; i < this.SavedCardList.size(); i++) {
/*  467 */         ((SaveCard)this.SavedCardList.get(i)).setCvv("");
/*      */       }
/*      */       
/*  470 */       this.adapter.notifyDataSetChanged();
/*      */       
/*  472 */       this.creditcard.setText("");
/*  473 */       this.ip_cvv.setText("");
/*  474 */       this.ip_exp.setText("");
/*  475 */       this.ll_saved_card.setVisibility(0);
/*  476 */       this.ll_new_card.setVisibility(8);
/*  477 */       if (this.promolist_spinner.getSelectedItemPosition() > 0) {
/*  478 */         apply_promo_saved();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fetch_payment_details() {
/*  487 */     LoadingDialog.showLoadingDialog(this.mContext, "Loading...");
/*      */     
/*  489 */     int a = 1;
/*  490 */     a = 3;
/*      */     
/*  492 */     StringRequest stringRequest = new StringRequest(1, "https://secure.ccavenue.ae/transaction/transaction.do", new com.android.volley.Response.Listener<String>()
/*      */         {
/*      */           
/*      */           public void onResponse(String response)
/*      */           {
/*  497 */             LoadingDialog.cancelLoading();
/*      */ 
/*      */ 
/*      */             
/*  501 */             if (response != null && !response.equals("")) {
/*  502 */               PaymentOptions.this.payment_list(response);
/*      */             } else {
/*      */               
/*  505 */               Log.e("ServiceHandler", "Couldn't get any data from the url");
/*      */             } 
/*      */           }
/*      */         },new com.android.volley.Response.ErrorListener()
/*      */         {
/*      */           public void onErrorResponse(VolleyError error)
/*      */           {
/*  512 */             LoadingDialog.cancelLoading();
/*      */           }
/*      */         })
/*      */       {
/*      */         protected Map<String, String> getParams()
/*      */         {
/*  518 */           Map<String, String> params = new HashMap<>();
/*  519 */           params.put("command", "getJsonDataVault");
/*  520 */           params.put("access_code", PaymentOptions.merchant.getAccess_code().trim());
/*  521 */           params.put("currency", PaymentOptions.merchant.getCurrency().trim());
/*  522 */           params.put("amount", PaymentOptions.merchant.getAmount().trim());
/*  523 */           params.put("customer_identifier", PaymentOptions.merchant.getCustomer_id().trim());
/*  524 */           return params;
/*      */         }
/*      */       };
/*      */ 
/*      */     
/*  529 */     RequestQueue requestQueue = Volley.newRequestQueue((Context)this);
/*  530 */     requestQueue.add((Request)stringRequest);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void get_promo_list() {
/*  536 */     RetrofitClient.GitApiInterface service = RetrofitClient.Indianrail();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  545 */     String body = "{\n\t\"accessCode\":\"" + merchant.getAccess_code() + "\"\n\t,\"currency\":\"" + merchant.getCurrency() + "\"\n\t,\"amount\":\"" + merchant.getAmount() + "\"\n\t,\"merchant_id\":\"" + merchant.getMerchant_id() + "\"\n\t,\"settingPromotions\":\"Y\"\n}";
/*      */ 
/*      */     
/*  548 */     Call<String> call = service.post_listing(body);
/*      */     
/*  550 */     call.enqueue(new Callback<String>()
/*      */         {
/*      */           public void onResponse(Call<String> call, retrofit2.Response<String> response) {
/*      */             try {
/*  554 */               PaymentOptions.promolist.clear();
/*  555 */               PaymentOptions.this.list_spinner.clear();
/*  556 */               PaymentOptions.this.list_spinner_code.clear();
/*      */ 
/*      */               
/*  559 */               if (response.body() != null) {
/*  560 */                 JSONArray ja = new JSONArray((String)response.body());
/*  561 */                 PaymentOptions.this.list_spinner.add("Select");
/*  562 */                 PaymentOptions.this.list_spinner_code.add("Select");
/*  563 */                 for (int i = 0; i < ja.length(); i++) {
/*  564 */                   JSONObject j = ja.getJSONObject(i);
/*  565 */                   Promotion p = new Promotion();
/*  566 */                   p.setDiscountValue(j.getString("discountValue"));
/*  567 */                   p.setNetAmt(j.getString("netAmt"));
/*  568 */                   p.setPromoCardName(j.getString("promoCardName"));
/*  569 */                   p.setPromoId(j.getString("promoId"));
/*  570 */                   p.setPromoName(j.getString("promoName"));
/*  571 */                   PaymentOptions.this.list_spinner_code.add(j.getString("promoId"));
/*  572 */                   PaymentOptions.this.list_spinner.add(j.getString("promoName"));
/*  573 */                   p.setPromoPayOptType(j.getString("promoPayOptType"));
/*  574 */                   p.setPromoPayOptTypeDesc(j.getString("promoPayOptTypeDesc"));
/*  575 */                   p.setPromoTandC(j.getString("promoTandC"));
/*  576 */                   p.setPromoTerms(j.getString("promoTerms"));
/*  577 */                   p.setPromotionDesc(j.getString("promotionDesc"));
/*  578 */                   p.setPromoType(j.getString("promoType"));
/*  579 */                   PaymentOptions.promolist.add(p);
/*      */                 } 
/*      */                 
/*  582 */                 ArrayAdapter<String> adp1 = new ArrayAdapter(PaymentOptions.this.getApplicationContext(), R.layout.spinner_item_promo, PaymentOptions.this.list_spinner);
/*      */                 
/*  584 */                 adp1.setDropDownViewResource(17367049);
/*  585 */                 PaymentOptions.this.promolist_spinner.setAdapter((SpinnerAdapter)adp1);
/*      */               }
/*      */               else {
/*      */                 
/*  589 */                 Toast.makeText((Context)PaymentOptions.this, "No Response. Please try Again!!!", 1).show();
/*  590 */                 PaymentOptions.this.finish();
/*      */               }
/*      */             
/*      */             }
/*  594 */             catch (Exception e) {
/*      */               
/*  596 */               Toast.makeText((Context)PaymentOptions.this, "Error. Please try Again!!!", 1).show();
/*  597 */               LoadingDialog.cancelLoading();
/*  598 */               PaymentOptions.this.finish();
/*      */             } 
/*      */           }



        /*      */
/*      */ 
/*      */           
/*      */           public void onFailure(Call<String> call, Throwable t) {
/*  605 */             if (t instanceof java.net.SocketTimeoutException) {
/*  606 */               LoadingDialog.cancelLoading();
/*  607 */               Toast.makeText((Context)PaymentOptions.this, "Something went wrong. Please try Again!!!", 1).show();
/*      */             } else {
/*      */               
/*  610 */               LoadingDialog.cancelLoading();
/*  611 */               Toast.makeText((Context)PaymentOptions.this, "Something went wrong. Please try Again!!!", 1).show();
/*      */             } 
/*      */             
/*  614 */             PaymentOptions.this.finish();
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getSettings() {
/*  625 */     RetrofitClient.GitApiInterface service = RetrofitClient.Indianrail();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  631 */     String body = "{\n\t\"accessCode\": \"" + merchant.getAccess_code() + "\"\n}";
/*      */     
/*  633 */     Call<String> call = service.post_setting(body);
/*      */     
/*  635 */     call.enqueue(new Callback<String>()
/*      */         {
/*      */ 
/*      */           
/*      */           public void onResponse(Call<String> call, retrofit2.Response<String> response)
/*      */           {
/*      */             try {
/*  642 */               if (response.body() != null)
/*      */               {
/*  644 */                 JSONObject j = new JSONObject(((String)response.body()).toString());
/*      */                 
/*  646 */                 if (PaymentOptions.merchant.isShow_addr()) {
/*  647 */                   PaymentOptions.this.ll_main_billing.setVisibility(0);
/*  648 */                   if (j.getString("vaultCardSaved").equalsIgnoreCase("Y")) {
/*  649 */                     Constants.VAULT = "Y";
/*  650 */                     PaymentOptions.this.checkBox1.setVisibility(0);
/*      */                   } else {
/*      */                     
/*  653 */                     Constants.VAULT = "N";
/*  654 */                     PaymentOptions.this.checkBox1.setVisibility(8);
/*  655 */                     PaymentOptions.this.savedcard.setVisibility(8);
/*      */                   } 
/*      */ 
/*      */                   
/*  659 */                   if (j.getString("settingPromotion").equalsIgnoreCase("Y") && PaymentOptions.merchant.isCCAvenue_promo()) {
/*  660 */                     PaymentOptions.this.get_promo_list();
/*  661 */                     Constants.PROMOTION = "Y";
/*  662 */                     LinearLayout.LayoutParams childParam1 = new LinearLayout.LayoutParams(0, -1);
/*  663 */                     childParam1.weight = 0.25F;
/*  664 */                     PaymentOptions.this.txt_billing.setLayoutParams((ViewGroup.LayoutParams)childParam1);
/*  665 */                     PaymentOptions.this.txt_shipping.setLayoutParams((ViewGroup.LayoutParams)childParam1);
/*  666 */                     PaymentOptions.this.txt_promo.setVisibility(0);
/*  667 */                     LinearLayout.LayoutParams childParam2 = new LinearLayout.LayoutParams(0, -1);
/*  668 */                     childParam2.weight = 0.5F;
/*  669 */                     PaymentOptions.this.txt_promo.setLayoutParams((ViewGroup.LayoutParams)childParam2);
/*  670 */                     PaymentOptions.this.txt_promo.setTextColor(Color.parseColor(PaymentOptions.this.inactiveTabTxt));
/*  671 */                     PaymentOptions.this.txt_promo.setBackgroundColor(Color.parseColor(PaymentOptions.this.inactiveTabBkg));
/*  672 */                     PaymentOptions.this.txt_promo.setOnClickListener(new View.OnClickListener()
/*      */                         {
/*      */                           public void onClick(View v) {
/*  675 */                             PaymentOptions.this.selected_billing = "promo";
/*      */                             
/*  677 */                             PaymentOptions.this.ll_billing_address.setVisibility(8);
/*  678 */                             PaymentOptions.this.ll_promo.setVisibility(0);
/*  679 */                             PaymentOptions.this.billing_name.setText(PaymentOptions.shipping.getName());
/*  680 */                             PaymentOptions.this.billing_add1.setText(PaymentOptions.shipping.getAddress());
/*  681 */                             PaymentOptions.this.billing_add2.setText(PaymentOptions.shipping.getCity() + " " + PaymentOptions.shipping.getState() + " " + PaymentOptions.shipping.getCountry());
/*  682 */                             PaymentOptions.this.billing_mob.setText(PaymentOptions.shipping.getTelephone());
/*  683 */                             PaymentOptions.this.billing_email.setVisibility(8);
/*  684 */                             PaymentOptions.this.txt_billing.setVisibility(0);
/*  685 */                             PaymentOptions.this.txt_shipping.setVisibility(0);
/*  686 */                             PaymentOptions.this.txt_promo.setTextColor(Color.parseColor(PaymentOptions.this.color_text));
/*  687 */                             PaymentOptions.this.txt_promo.setBackgroundColor(PaymentOptions.this.getResources().getColor(R.color.white));
/*      */                             
/*  689 */                             PaymentOptions.this.txt_billing.setTextColor(Color.parseColor(PaymentOptions.this.inactiveTabTxt));
/*  690 */                             PaymentOptions.this.txt_billing.setBackgroundColor(Color.parseColor(PaymentOptions.this.inactiveTabBkg));
/*      */                             
/*  692 */                             PaymentOptions.this.txt_shipping.setTextColor(Color.parseColor(PaymentOptions.this.inactiveTabTxt));
/*  693 */                             PaymentOptions.this.txt_shipping.setBackgroundColor(Color.parseColor(PaymentOptions.this.inactiveTabBkg));
/*      */                           }
/*      */                         });
/*      */                   
/*      */                   }
/*      */                   else {
/*      */ 
/*      */                     
/*  701 */                     Constants.PROMOTION = "N";
/*      */                   } 
/*      */ 
/*      */ 
/*      */                   
/*  706 */                   if (j.getString("settingBillingInformation").equalsIgnoreCase("Y")) {
/*  707 */                     Constants.BILLING = "Y";
/*  708 */                     if (j.getString("settingShippingInformation").equalsIgnoreCase("Y")) {
/*      */                       
/*  710 */                       Constants.SHIPPING = "Y";
/*  711 */                       PaymentOptions.this.fetch_payment_details();
/*      */                     } else {
/*  713 */                       Constants.SHIPPING = "N";
/*  714 */                       PaymentOptions.this.fetch_payment_details();
/*      */                     }
/*      */                   
/*      */                   } else {
/*      */                     
/*  719 */                     Constants.BILLING = "N";
/*  720 */                     if (j.getString("settingShippingInformation").equalsIgnoreCase("Y")) {
/*      */                       
/*  722 */                       Constants.SHIPPING = "Y";
/*  723 */                       PaymentOptions.this.fetch_payment_details();
/*      */                     } else {
/*  725 */                       Constants.SHIPPING = "N";
/*  726 */                       PaymentOptions.this.fetch_payment_details();
/*      */                     }
/*      */                   
/*      */                   }
/*      */                 
/*      */                 } else {
/*      */                   
/*  733 */                   PaymentOptions.this.ll_main_billing.setVisibility(8);
/*  734 */                   if (j.getString("vaultCardSaved").equalsIgnoreCase("Y")) {
/*  735 */                     Constants.VAULT = "Y";
/*  736 */                     PaymentOptions.this.checkBox1.setVisibility(0);
/*      */                   } else {
/*  738 */                     Constants.VAULT = "N";
/*  739 */                     PaymentOptions.this.checkBox1.setVisibility(8);
/*  740 */                     PaymentOptions.this.savedcard.setVisibility(8);
/*      */                   } 
/*  742 */                   if (j.getString("settingBillingInformation").equalsIgnoreCase("Y"))
/*  743 */                   { Constants.BILLING = "Y";
/*  744 */                     boolean chk = PaymentOptions.this.validate_billing();
/*  745 */                     if (chk) {
/*  746 */                       if (j.getString("settingShippingInformation").equalsIgnoreCase("Y"))
/*      */                       
/*  748 */                       { Constants.SHIPPING = "Y";
/*  749 */                         boolean chk1 = PaymentOptions.this.validate_shipping();
/*  750 */                         if (chk1) {
/*  751 */                           PaymentOptions.this.fetch_payment_details();
/*      */                         } else {
/*  753 */                           PaymentOptions.this.finish();
/*      */                         }  }
/*  755 */                       else { Constants.SHIPPING = "N";
/*      */                         
/*  757 */                         PaymentOptions.this.fetch_payment_details(); }
/*      */                     
/*      */                     } else {
/*      */                       
/*  761 */                       PaymentOptions.this.finish();
/*      */                     }  }
/*  763 */                   else { Constants.BILLING = "N";
/*      */                     
/*  765 */                     if (j.getString("settingShippingInformation").equalsIgnoreCase("Y"))
/*      */                     
/*  767 */                     { Constants.SHIPPING = "Y";
/*  768 */                       boolean chk = PaymentOptions.this.validate_shipping();
/*  769 */                       if (chk) {
/*  770 */                         PaymentOptions.this.fetch_payment_details();
/*      */                       } else {
/*  772 */                         PaymentOptions.this.finish();
/*      */                       }  }
/*  774 */                     else { Constants.SHIPPING = "N";
/*      */                       
/*  776 */                       PaymentOptions.this.fetch_payment_details(); }
/*      */                      }
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/*  782 */                   if (j.getString("settingPromotion").equalsIgnoreCase("Y") && PaymentOptions.merchant.isCCAvenue_promo()) {
/*  783 */                     PaymentOptions.this.ll_main_billing.setVisibility(0);
/*      */                     
/*  785 */                     if (j.getString("settingPromotion").equalsIgnoreCase("Y")) {
/*  786 */                       PaymentOptions.this.get_promo_list();
/*  787 */                       Constants.PROMOTION = "Y";
/*  788 */                       LinearLayout.LayoutParams childParam1 = new LinearLayout.LayoutParams(0, -1);
/*  789 */                       childParam1.weight = 0.25F;
/*  790 */                       PaymentOptions.this.txt_billing.setLayoutParams((ViewGroup.LayoutParams)childParam1);
/*  791 */                       PaymentOptions.this.txt_shipping.setLayoutParams((ViewGroup.LayoutParams)childParam1);
/*      */                       
/*  793 */                       PaymentOptions.this.txt_billing.setVisibility(8);
/*  794 */                       PaymentOptions.this.txt_shipping.setVisibility(8);
/*  795 */                       PaymentOptions.this.txt_promo.setVisibility(0);
/*  796 */                       LinearLayout.LayoutParams childParam2 = new LinearLayout.LayoutParams(0, -1);
/*  797 */                       childParam2.weight = 0.5F;
/*  798 */                       PaymentOptions.this.txt_promo.setLayoutParams((ViewGroup.LayoutParams)childParam2);
/*      */ 
/*      */                       
/*  801 */                       PaymentOptions.this.selected_billing = "promo";
/*      */                       
/*  803 */                       PaymentOptions.this.ll_billing_address.setVisibility(8);
/*  804 */                       PaymentOptions.this.ll_promo.setVisibility(0);
/*  805 */                       PaymentOptions.this.head.setBackgroundColor(Color.parseColor(PaymentOptions.this.inactiveTabBkg));
/*  806 */                       PaymentOptions.this.txt_promo.setTextColor(Color.parseColor(PaymentOptions.this.color_text));
/*  807 */                       PaymentOptions.this.txt_promo.setBackgroundColor(PaymentOptions.this.getResources().getColor(R.color.white));
/*      */ 
/*      */                     
/*      */                     }
/*      */                     else {
/*      */ 
/*      */                       
/*  814 */                       Constants.PROMOTION = "N";
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  823 */                 if (j.getString("settingDiscountCoupon").equalsIgnoreCase("Y")) {
/*  824 */                   Constants.DISCOUNT = "Y";
/*  825 */                   PaymentOptions.this.ll_coupon.setVisibility(0);
/*      */                 } else {
/*  827 */                   Constants.DISCOUNT = "N";
/*  828 */                   PaymentOptions.this.ll_coupon.setVisibility(8);
/*      */                 
/*      */                 }
/*      */               
/*      */               }
/*      */               else
/*      */               {
/*  835 */                 Toast.makeText((Context)PaymentOptions.this, "No Response. Please try Again!!!", 1).show();
/*  836 */                 PaymentOptions.this.finish();
/*      */               }
/*      */             
/*      */             }
/*  840 */             catch (Exception e) {
/*      */               
/*  842 */               Toast.makeText((Context)PaymentOptions.this, "Error. Please try Again!!!", 1).show();
/*  843 */               LoadingDialog.cancelLoading();
/*  844 */               PaymentOptions.this.finish();
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*      */           public void onFailure(Call<String> call, Throwable t) {
/*  851 */             if (t instanceof java.net.SocketTimeoutException) {
/*  852 */               LoadingDialog.cancelLoading();
/*  853 */               Toast.makeText((Context)PaymentOptions.this, "Something went wrong. Please try Again!!!", 1).show();
/*      */             } else {
/*      */               
/*  856 */               LoadingDialog.cancelLoading();
/*  857 */               Toast.makeText((Context)PaymentOptions.this, "Something went wrong. Please try Again!!!", 1).show();
/*      */             } 
/*      */             
/*  860 */             PaymentOptions.this.finish();
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void payment_list(String response) {
/*      */     try {
/*  870 */       this.jsonRespObj = new JSONObject(response);
/*  871 */       if (this.jsonRespObj != null)
/*      */       {
/*  873 */         if (this.jsonRespObj.getString("payOptions") != null) {
/*  874 */           JSONArray vPayOptsArr = new JSONArray(this.jsonRespObj.getString("payOptions"));
/*  875 */           for (int i = 0; i < vPayOptsArr.length(); i++) {
/*  876 */             JSONObject vPaymentOption = vPayOptsArr.getJSONObject(i);
/*  877 */             if (!vPaymentOption.getString("payOpt").equals("OPTIVRS")) {
/*  878 */               this.payOptionList.add(new PaymentOptionDTO(vPaymentOption.getString("payOpt"), vPaymentOption.getString("payOptDesc").toString()));
/*  879 */               this.paymentOptions.put(vPaymentOption.getString("payOpt"), vPaymentOption.getString("payOptDesc"));
/*      */               try {
/*  881 */                 JSONArray vCardArr = new JSONArray(vPaymentOption.getString("cardsList"));
/*  882 */                 if (vCardArr.length() > 0) {
/*  883 */                   this.cardsList.put(vPaymentOption.getString("payOpt"), new ArrayList<>());
/*  884 */                   for (int j = 0; j < vCardArr.length(); j++) {
/*  885 */                     JSONObject card = vCardArr.getJSONObject(j);
/*      */                     try {
/*  887 */                       CardTypeDTO cardTypeDTO = new CardTypeDTO();
/*  888 */                       cardTypeDTO.setCardName(card.getString("cardName"));
/*  889 */                       cardTypeDTO.setCardType(card.getString("cardType"));
/*  890 */                       cardTypeDTO.setPayOptType(card.getString("payOptType"));
/*  891 */                       cardTypeDTO.setDataAcceptedAt(card.getString("dataAcceptedAt"));
/*  892 */                       cardTypeDTO.setStatus(card.getString("status"));
/*      */                       
/*  894 */                       ((ArrayList<CardTypeDTO>)this.cardsList.get(vPaymentOption.getString("payOpt"))).add(cardTypeDTO);
/*  895 */                     } catch (Exception e) {
/*  896 */                       Log.e("ServiceHandler", "Error parsing cardType", e);
/*      */                     } 
/*      */                   } 
/*      */                 } 
/*  900 */               } catch (Exception e) {
/*  901 */                 Log.e("ServiceHandler", "Error parsing payment option", e);
/*      */               }
/*      */             
/*      */             }
/*      */           
/*      */           } 
/*      */         } 
/*      */       }
/*  909 */     } catch (JSONException e) {
/*  910 */       Log.e("ServiceHandler", "Error fetching data from server", (Throwable)e);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  916 */       if (this.jsonRespObj.has("CustPayments")) {
/*      */         
/*  918 */         this.ll_saved_card.setVisibility(0);
/*  919 */         this.ll_new_card.setVisibility(8);
/*      */ 
/*      */         
/*  922 */         JSONArray vJsonArr = new JSONArray(this.jsonRespObj.getString("CustPayments"));
/*      */ 
/*      */ 
/*      */         
/*      */         try {
/*  927 */           for (int i = 0; i < vJsonArr.length(); i++)
/*      */           {
/*  929 */             JSONObject j = vJsonArr.getJSONObject(i);
/*  930 */             if (j.getString("payCardType").equalsIgnoreCase("CRDC") || j.getString("payCardType").equalsIgnoreCase("DBCRD")) {
/*  931 */               SaveCard sc = new SaveCard();
/*  932 */               sc.setPayOptId(j.getString("payOptId"));
/*  933 */               sc.setPayOption(j.getString("payOption"));
/*  934 */               sc.setPayCardType(j.getString("payCardType"));
/*  935 */               sc.setPayCardName(j.getString("payCardName"));
/*  936 */               sc.setPayCardNo(j.getString("payCardNo"));
/*  937 */               sc.setCvv("");
/*  938 */               this.cvv_list.add("");
/*  939 */               this.SavedCardList.add(sc);
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */         
/*      */         }
/*  946 */         catch (JSONException e) {
/*  947 */           e.printStackTrace();
/*      */         } 
/*      */         
/*  950 */         this.viewPager = (ViewPager)findViewById(R.id.pager);
/*      */ 
/*      */ 
/*      */         
/*  954 */         this.viewPager.setClipToPadding(false);
/*      */         
/*  956 */         this.viewPager.setPadding(120, 0, 120, 0);
/*      */         
/*  958 */         this.viewPager.setPageMargin(10);
/*      */         
/*  960 */         this.adapter = new ViewPagerAdapter((Context)this, this.SavedCardList, new OnEditTextChanged()
/*      */             {
/*      */               public void onTextChanged(String charSeq)
/*      */               {
/*  964 */                 PaymentOptions.this.updateTotalValue(charSeq);
/*      */               }
/*      */             });
/*      */ 
/*      */ 
/*      */         
/*  970 */         this.viewPager.setAdapter(this.adapter);
/*      */ 
/*      */         
/*  973 */         this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
/*      */             {
/*      */               public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               public void onPageSelected(int position) {
/*  981 */                 PaymentOptions.pos_page = position;
/*  982 */                 for (int i = 0; i < PaymentOptions.this.SavedCardList.size(); i++) {
/*  983 */                   ((SaveCard)PaymentOptions.this.SavedCardList.get(i)).setCvv("");
/*      */                 }
/*      */                 
/*  986 */                 PaymentOptions.this.adapter.notifyDataSetChanged();
/*      */ 
/*      */                 
/*  989 */                 if (PaymentOptions.pos > 0) {
/*  990 */                   PaymentOptions.this.apply_promo_saved();
/*      */                 }
/*      */               }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               public void onPageScrollStateChanged(int state) {}
/*      */             });
/*      */       } else {
/* 1005 */         this.ll_saved_card.setVisibility(8);
/* 1006 */         this.ll_new_card.setVisibility(0);
/* 1007 */         if (merchant.customer_id.length() < 1)
/* 1008 */           this.checkBox1.setVisibility(8); 
/* 1009 */         this.savedcard.setVisibility(8);
/* 1010 */         this.payother.setVisibility(8);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1015 */       for (int oo = 0; oo < this.payOptionList.size(); oo++) {
/* 1016 */         PaymentType nn1 = new PaymentType();
/* 1017 */         nn1.setName(((PaymentOptionDTO)this.payOptionList.get(oo)).getPayOptName());
/* 1018 */         this.movieArrayList.add(nn1);
/*      */       } 
/*      */       
/* 1021 */       this.movieAdapter = new MovieAdapter((Context)this, this.movieArrayList, this.jsonRespObj.toString());
/*      */     
/*      */     }
/* 1024 */     catch (JSONException e) {
/* 1025 */       e.printStackTrace();
/*      */     } 
/* 1027 */     after_hit();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void showToast(String msg) {
/* 1033 */     Toast.makeText((Context)this, msg, 1).show();
/*      */   }
/*      */ 
/*      */   
/*      */   public void initialiseToolbar(String toolbarTitle) {
/* 1038 */     setSupportActionBar(this.mToolbar);
/* 1039 */     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/* 1040 */     getSupportActionBar().setDisplayShowHomeEnabled(true);
/* 1041 */     getSupportActionBar().setTitle(toolbarTitle);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean onOptionsItemSelected(MenuItem menuItem) {
/* 1046 */     if (menuItem.getItemId() == 16908332) {
/* 1047 */       onBackPressed();
/*      */     }
/* 1049 */     return super.onOptionsItemSelected(menuItem);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onBackPressed() {
/* 1055 */     AlertDialog alertDialog = (new AlertDialog.Builder((Context)this)).create();
/*      */ 
/*      */     
/* 1058 */     alertDialog.setMessage("Do you really want to cancel this transaction?");
/*      */     
/* 1060 */     alertDialog.setButton(-2, "CANCEL", new DialogInterface.OnClickListener()
/*      */         {
/*      */           public void onClick(DialogInterface dialog, int which) {}
/*      */         });
/*      */ 
/*      */ 
/*      */     
/* 1067 */     alertDialog.setButton(-1, "OK", new DialogInterface.OnClickListener()
/*      */         {
/*      */           public void onClick(DialogInterface dialog, int which)
/*      */           {
/* 1071 */             CustomModel.getInstance().changeState("Transaction Closed");
/* 1072 */             PaymentOptions.this.finish();
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 1077 */     alertDialog.show();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateTotalValue(String str) {
/* 1084 */     this.cvv_list.set(this.viewPager.getCurrentItem(), str);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void pay_saved() {
/* 1090 */     if (((String)this.cvv_list.get(this.viewPager.getCurrentItem())).length() == 3) {
/*      */       
/* 1092 */       Intent intent = new Intent((Context)this, WebViewActivity.class);
/* 1093 */       intent.putExtra("order_id", ServiceUtility.chkNull(merchant.getOrder_id()).toString().trim());
/* 1094 */       intent.putExtra("access_code", ServiceUtility.chkNull(merchant.getAccess_code()).toString().trim());
/* 1095 */       intent.putExtra("merchant_id", ServiceUtility.chkNull(merchant.getMerchant_id()).toString().trim());
/* 1096 */       intent.putExtra("billing_name", ServiceUtility.chkNull(billing.getName()).toString().trim());
/* 1097 */       intent.putExtra("billing_address", ServiceUtility.chkNull(billing.getAddress()).toString().trim());
/* 1098 */       intent.putExtra("billing_country", ServiceUtility.chkNull(billing.getCountry()).toString().trim());
/* 1099 */       intent.putExtra("billing_state", ServiceUtility.chkNull(billing.getState()).toString().trim());
/* 1100 */       intent.putExtra("billing_city", ServiceUtility.chkNull(billing.getCity()).toString().trim());
/*      */       
/* 1102 */       intent.putExtra("billing_tel", ServiceUtility.chkNull(billing.getTelephone()).toString().trim());
/* 1103 */       intent.putExtra("billing_email", ServiceUtility.chkNull(billing.getEmail()).toString().trim());
/* 1104 */       intent.putExtra("delivery_name", ServiceUtility.chkNull(shipping.getName()).toString().trim());
/* 1105 */       intent.putExtra("delivery_address", ServiceUtility.chkNull(shipping.getAddress()).toString().trim());
/* 1106 */       intent.putExtra("delivery_country", ServiceUtility.chkNull(shipping.getCountry()).toString().trim());
/* 1107 */       intent.putExtra("delivery_state", ServiceUtility.chkNull(shipping.getState()).toString().trim());
/* 1108 */       intent.putExtra("delivery_city", ServiceUtility.chkNull(shipping.getCity()).toString().trim());
/*      */       
/* 1110 */       intent.putExtra("delivery_tel", ServiceUtility.chkNull(shipping.getTelephone()).toString().trim());
/* 1111 */       intent.putExtra("cvv_number", ServiceUtility.chkNull(this.cvv_list.get(this.viewPager.getCurrentItem())).toString().trim());
/* 1112 */       intent.putExtra("redirect_url", ServiceUtility.chkNull(merchant.getRedirect_url()).toString().trim());
/* 1113 */       intent.putExtra("cancel_url", ServiceUtility.chkNull(merchant.getCancel_url()).toString().trim());
/* 1114 */       intent.putExtra("rsa_key_url", ServiceUtility.chkNull(merchant.getRsa_url()).toString().trim());
/* 1115 */       intent.putExtra("payment_option", ((SaveCard)this.SavedCardList.get(this.viewPager.getCurrentItem())).getPayOption());
/* 1116 */       intent.putExtra("card_number", ServiceUtility.chkNull(((SaveCard)this.SavedCardList.get(this.viewPager.getCurrentItem())).getPayCardNo()).toString().trim());
/* 1117 */       intent.putExtra("expiry_year", ServiceUtility.chkNull("").toString().trim());
/* 1118 */       intent.putExtra("expiry_month", ServiceUtility.chkNull("").toString().trim());
/* 1119 */       intent.putExtra("issuing_bank", ServiceUtility.chkNull("").toString().trim());
/*      */       
/* 1121 */       intent.putExtra("card_type", ((SaveCard)this.SavedCardList.get(this.viewPager.getCurrentItem())).getPayCardType());
/* 1122 */       intent.putExtra("card_name", ((SaveCard)this.SavedCardList.get(this.viewPager.getCurrentItem())).getPayCardName());
/* 1123 */       intent.putExtra("data_accept", "");
/* 1124 */       intent.putExtra("customer_identifier", merchant.getCustomer_id());
/* 1125 */       intent.putExtra("currency", ServiceUtility.chkNull(merchant.getCurrency()).toString().trim());
/* 1126 */       intent.putExtra("amount", ServiceUtility.chkNull(merchant.getAmount()).toString().trim());
/*      */       
/* 1128 */       if (standardInstructions.getSi_type().equals("FIXED")) {
/*      */         
/* 1130 */         intent.putExtra("si_type", standardInstructions.getSi_type());
/* 1131 */         intent.putExtra("si_mer_ref_no", standardInstructions.getSi_mer_ref_no());
/* 1132 */         intent.putExtra("si_is_setup_amt", standardInstructions.getSi_is_setup_amt());
/* 1133 */         intent.putExtra("si_amount", standardInstructions.getSi_amount());
/* 1134 */         intent.putExtra("si_start_date", standardInstructions.getSi_start_date());
/* 1135 */         intent.putExtra("si_frequency_type", standardInstructions.getSi_frequency_type());
/* 1136 */         intent.putExtra("si_frequency", standardInstructions.getSi_frequency());
/* 1137 */         intent.putExtra("si_bill_cycle", standardInstructions.getSi_bill_cycle());
/*      */       }
/* 1139 */       else if (standardInstructions.getSi_type().equals("ONDEMAND")) {
/* 1140 */         intent.putExtra("si_type", standardInstructions.getSi_type());
/* 1141 */         intent.putExtra("si_mer_ref_no", standardInstructions.getSi_mer_ref_no());
/* 1142 */         intent.putExtra("si_is_setup_amt", standardInstructions.getSi_is_setup_amt());
/* 1143 */         intent.putExtra("si_amount", "");
/* 1144 */         intent.putExtra("si_start_date", standardInstructions.getSi_start_date());
/* 1145 */         intent.putExtra("si_frequency_type", "");
/* 1146 */         intent.putExtra("si_frequency", "");
/* 1147 */         intent.putExtra("si_bill_cycle", "");
/*      */       } 
/*      */       
/* 1150 */       if (this.promo_txt.getText().toString().equalsIgnoreCase("Promotion applied")) {
/* 1151 */         intent.putExtra("promo_code", ServiceUtility.chkNull(((Promotion)promolist.get(pos - 1)).getPromoId()).toString().trim());
/*      */       }
/* 1153 */       finish();
/* 1154 */       startActivity(intent);
/*      */     }
/*      */     else {
/*      */       
/* 1158 */       Toast.makeText(getApplicationContext(), "Enter valid CVV", 1).show();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void pay_new() {
/* 1164 */     Intent intent = new Intent((Context)this, WebViewActivity.class);
/* 1165 */     intent.putExtra("order_id", ServiceUtility.chkNull(merchant.getOrder_id()).toString().trim());
/* 1166 */     intent.putExtra("access_code", ServiceUtility.chkNull(merchant.getAccess_code()).toString().trim());
/* 1167 */     intent.putExtra("merchant_id", ServiceUtility.chkNull(merchant.getMerchant_id()).toString().trim());
/* 1168 */     intent.putExtra("billing_name", ServiceUtility.chkNull(billing.getName()).toString().trim());
/* 1169 */     intent.putExtra("billing_address", ServiceUtility.chkNull(billing.getAddress()).toString().trim());
/* 1170 */     intent.putExtra("billing_country", ServiceUtility.chkNull(billing.getCountry()).toString().trim());
/* 1171 */     intent.putExtra("billing_state", ServiceUtility.chkNull(billing.getState()).toString().trim());
/* 1172 */     intent.putExtra("billing_city", ServiceUtility.chkNull(billing.getCity()).toString().trim());
/*      */     
/* 1174 */     intent.putExtra("billing_tel", ServiceUtility.chkNull(billing.getTelephone()).toString().trim());
/* 1175 */     intent.putExtra("billing_email", ServiceUtility.chkNull(billing.getEmail()).toString().trim());
/* 1176 */     intent.putExtra("delivery_name", ServiceUtility.chkNull(shipping.getName()).toString().trim());
/* 1177 */     intent.putExtra("delivery_address", ServiceUtility.chkNull(shipping.getAddress()).toString().trim());
/* 1178 */     intent.putExtra("delivery_country", ServiceUtility.chkNull(shipping.getCountry()).toString().trim());
/* 1179 */     intent.putExtra("delivery_state", ServiceUtility.chkNull(shipping.getState()).toString().trim());
/* 1180 */     intent.putExtra("delivery_city", ServiceUtility.chkNull(shipping.getCity()).toString().trim());
/*      */     
/* 1182 */     intent.putExtra("delivery_tel", ServiceUtility.chkNull(shipping.getTelephone()).toString().trim());
/* 1183 */     intent.putExtra("cvv_number", this.ip_cvv.getText().toString());
/* 1184 */     intent.putExtra("redirect_url", ServiceUtility.chkNull(merchant.getRedirect_url()).toString().trim());
/* 1185 */     intent.putExtra("cancel_url", ServiceUtility.chkNull(merchant.getCancel_url()).toString().trim());
/* 1186 */     intent.putExtra("rsa_key_url", ServiceUtility.chkNull(merchant.getRsa_url()).toString().trim());
/* 1187 */     intent.putExtra("payment_option", "");
/* 1188 */     intent.putExtra("card_number", ServiceUtility.chkNull(this.creditcard.getText()).toString().replaceAll(" ", "").trim());
/* 1189 */     intent.putExtra("expiry_month", ServiceUtility.chkNull(this.ip_exp.getText().toString().substring(0, 2)).toString().trim());
/* 1190 */     intent.putExtra("expiry_year", ServiceUtility.chkNull("20" + this.ip_exp.getText().toString().substring(3, 5)).toString().trim());
/* 1191 */     intent.putExtra("issuing_bank", "");
/*      */ 
/*      */     
/* 1194 */     if (standardInstructions.getSi_type().equals("FIXED")) {
/*      */       
/* 1196 */       intent.putExtra("si_type", standardInstructions.getSi_type());
/* 1197 */       intent.putExtra("si_mer_ref_no", standardInstructions.getSi_mer_ref_no());
/* 1198 */       intent.putExtra("si_is_setup_amt", standardInstructions.getSi_is_setup_amt());
/* 1199 */       intent.putExtra("si_amount", standardInstructions.getSi_amount());
/* 1200 */       intent.putExtra("si_start_date", standardInstructions.getSi_start_date());
/* 1201 */       intent.putExtra("si_frequency_type", standardInstructions.getSi_frequency_type());
/* 1202 */       intent.putExtra("si_frequency", standardInstructions.getSi_frequency());
/* 1203 */       intent.putExtra("si_bill_cycle", standardInstructions.getSi_bill_cycle());
/*      */     }
/* 1205 */     else if (standardInstructions.getSi_type().equals("ONDEMAND")) {
/* 1206 */       intent.putExtra("si_type", standardInstructions.getSi_type());
/* 1207 */       intent.putExtra("si_mer_ref_no", standardInstructions.getSi_mer_ref_no());
/* 1208 */       intent.putExtra("si_is_setup_amt", standardInstructions.getSi_is_setup_amt());
/* 1209 */       intent.putExtra("si_start_date", standardInstructions.getSi_start_date());
/*      */     } 
/*      */     
/* 1212 */     if (this.checkBox1.isChecked() && 
/* 1213 */       standardInstructions.getSi_type().equals(""))
/*      */     {
/*      */       
/* 1216 */       intent.putExtra("saveCard", "Y");
/*      */     }
/* 1218 */     intent.putExtra("card_type", "");
/* 1219 */     intent.putExtra("card_name", "");
/* 1220 */     intent.putExtra("data_accept", "");
/* 1221 */     intent.putExtra("customer_identifier", merchant.getCustomer_id());
/* 1222 */     intent.putExtra("currency", ServiceUtility.chkNull(merchant.getCurrency()).toString().trim());
/* 1223 */     intent.putExtra("amount", ServiceUtility.chkNull(merchant.getAmount()).toString().trim());
/* 1224 */     if (this.promo_txt.getText().toString().equalsIgnoreCase("Promotion applied")) {
/* 1225 */       intent.putExtra("promo_code", ServiceUtility.chkNull(((Promotion)promolist.get(pos - 1)).getPromoId()).toString().trim());
/*      */     }
/* 1227 */     finish();
/* 1228 */     startActivity(intent);
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean Check(String ccNumber) {
/* 1233 */     int sum = 0;
/* 1234 */     boolean alternate = false;
/* 1235 */     for (int i = ccNumber.length() - 1; i >= 0; i--) {
/* 1236 */       int n = Integer.parseInt(ccNumber.substring(i, i + 1));
/* 1237 */       if (alternate) {
/* 1238 */         n *= 2;
/* 1239 */         if (n > 9) {
/* 1240 */           n = n % 10 + 1;
/*      */         }
/*      */       } 
/* 1243 */       sum += n;
/* 1244 */       alternate = !alternate;
/*      */     } 
/* 1246 */     return (sum % 10 == 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean validate_billing() {
/* 1251 */     if (billing.getName() == null || billing.getName().length() < 1) {
/* 1252 */       showToast("Invalid billing name!!!");
/* 1253 */       return false;
/*      */     } 
/*      */     
/* 1256 */     if (billing.getAddress() == null || billing.getAddress().length() < 1) {
/* 1257 */       showToast("Invalid billing Address!!!");
/* 1258 */       return false;
/*      */     } 
/* 1260 */     if (billing.getCity() == null || billing.getCity().length() < 1) {
/* 1261 */       showToast("Invalid billing City!!!");
/* 1262 */       return false;
/*      */     } 
/*      */     
/* 1265 */     if (billing.getCountry() == null || billing.getCountry().length() < 1) {
/* 1266 */       showToast("Invalid billing Country!!!");
/* 1267 */       return false;
/*      */     } 
/*      */     
/* 1270 */     if (billing.getTelephone() == null || billing.getTelephone().length() < 1) {
/* 1271 */       showToast("Invalid billing Telephone!!!");
/* 1272 */       return false;
/*      */     } 
/*      */     
/* 1275 */     if (billing.getEmail() == null || billing.getEmail().length() < 1) {
/* 1276 */       showToast("Invalid billing Email!!!");
/* 1277 */       return false;
/*      */     } 
/* 1279 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean validate_shipping() {
/* 1284 */     if (shipping.getName() == null || shipping.getName().length() < 1) {
/* 1285 */       showToast("Invalid shipping name!!!");
/* 1286 */       return false;
/*      */     } 
/*      */     
/* 1289 */     if (shipping.getAddress() == null || shipping.getAddress().length() < 1) {
/* 1290 */       showToast("Invalid shipping Address!!!");
/* 1291 */       return false;
/*      */     } 
/* 1293 */     if (shipping.getCity() == null || shipping.getCity().length() < 1) {
/* 1294 */       showToast("Invalid shipping City!!!");
/* 1295 */       return false;
/*      */     } 
/*      */     
/* 1298 */     if (shipping.getCountry() == null || shipping.getCountry().length() < 1) {
/* 1299 */       showToast("Invalid shipping Country!!!");
/* 1300 */       return false;
/*      */     } 
/*      */     
/* 1303 */     if (shipping.getTelephone() == null || shipping.getTelephone().length() < 1) {
/* 1304 */       showToast("Invalid shipping Telephone!!!");
/* 1305 */       return false;
/*      */     } 
/* 1307 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validate_all() {
/* 1314 */     if (billing.getName() == null || billing.getName().length() < 1) {
/* 1315 */       showToast("Invalid billing name!!!");
/* 1316 */       return false;
/*      */     } 
/*      */     
/* 1319 */     if (billing.getAddress() == null || billing.getAddress().length() < 1) {
/* 1320 */       showToast("Invalid billing Address!!!");
/* 1321 */       return false;
/*      */     } 
/* 1323 */     if (billing.getCity() == null || billing.getCity().length() < 1) {
/* 1324 */       showToast("Invalid billing City!!!");
/* 1325 */       return false;
/*      */     } 
/* 1327 */     if (billing.getState() == null || billing.getState().length() < 1) {
/* 1328 */       showToast("Invalid billing State!!!");
/* 1329 */       return false;
/*      */     } 
/* 1331 */     if (billing.getCountry() == null || billing.getCountry().length() < 1) {
/* 1332 */       showToast("Invalid billing Country!!!");
/* 1333 */       return false;
/*      */     } 
/*      */     
/* 1336 */     if (billing.getTelephone() == null || billing.getTelephone().length() < 1) {
/* 1337 */       showToast("Invalid billing Telephone!!!");
/* 1338 */       return false;
/*      */     } 
/*      */     
/* 1341 */     if (billing.getEmail() == null || billing.getEmail().length() < 1) {
/* 1342 */       showToast("Invalid billing Email!!!");
/* 1343 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 1347 */     if (shipping.getName() == null || shipping.getName().length() < 1) {
/* 1348 */       showToast("Invalid shipping name!!!");
/* 1349 */       return false;
/*      */     } 
/*      */     
/* 1352 */     if (shipping.getAddress() == null || shipping.getAddress().length() < 1) {
/* 1353 */       showToast("Invalid shipping Address!!!");
/* 1354 */       return false;
/*      */     } 
/* 1356 */     if (shipping.getCity() == null || shipping.getCity().length() < 1) {
/* 1357 */       showToast("Invalid shipping City!!!");
/* 1358 */       return false;
/*      */     } 
/* 1360 */     if (shipping.getState() == null || shipping.getState().length() < 1) {
/* 1361 */       showToast("Invalid shipping State!!!");
/* 1362 */       return false;
/*      */     } 
/* 1364 */     if (shipping.getCountry() == null || shipping.getCountry().length() < 1) {
/* 1365 */       showToast("Invalid shipping Country!!!");
/* 1366 */       return false;
/*      */     } 
/*      */     
/* 1369 */     if (shipping.getTelephone() == null || shipping.getTelephone().length() < 1) {
/* 1370 */       showToast("Invalid shipping Telephone!!!");
/* 1371 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 1375 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean validate_merchant() {
/* 1381 */     if (merchant.getAccess_code() == null || merchant.getAccess_code().length() < 1) {
/* 1382 */       showToast("Invalid Access Code!!!");
/* 1383 */       return false;
/*      */     } 
/*      */     
/* 1386 */     if (merchant.getOrder_id() == null || merchant.getOrder_id().length() < 1) {
/* 1387 */       showToast("Invalid Order id!!!");
/* 1388 */       return false;
/*      */     } 
/*      */     
/* 1391 */     if (merchant.getCurrency() == null || merchant.getCurrency().length() < 1) {
/* 1392 */       showToast("Invalid Currency!!!");
/* 1393 */       return false;
/*      */     } 
/*      */     
/* 1396 */     if (merchant.getAmount() == null || merchant.getAmount().length() < 1) {
/* 1397 */       showToast("Invalid Amount!!!");
/* 1398 */       return false;
/*      */     } 
/*      */     
/* 1401 */     if (merchant.getMerchant_id() == null || merchant.getMerchant_id().length() < 1) {
/* 1402 */       showToast("Invalid Merchant id!!!");
/* 1403 */       return false;
/*      */     } 
/*      */     
/* 1406 */     if (merchant.getRedirect_url() == null || merchant.getRedirect_url().length() < 1) {
/* 1407 */       showToast("hmm. Invalid redirect url!!!");
/* 1408 */       return false;
/*      */     } 
/*      */ 
/*      */     
/* 1412 */     if (merchant.getCancel_url() == null || merchant.getCancel_url().length() < 1) {
/* 1413 */       showToast("Invalid cancel url!!!");
/* 1414 */       return false;
/*      */     } 
/*      */     
/* 1417 */     if (merchant.getRsa_url() == null || merchant.getRsa_url().length() < 1) {
/* 1418 */       showToast("Invalid rsa url!!!");
/* 1419 */       return false;
/*      */     } 
/* 1421 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void onAddText(String text) {
/* 1427 */     validate_promo(text);
/*      */   }
/*      */   
/*      */   public void apply_promo() {
/* 1431 */     LoadingDialog.showLoadingDialog((Context)this, "Loading....");
/* 1432 */     RetrofitClient.GitApiInterface service = RetrofitClient.Indianrail();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1448 */     String body = "{\n\t\"promoCode\":\"" + ((Promotion)promolist.get(pos - 1)).getPromoId() + "\"\n\t,\"currency\":\"" + merchant.getCurrency() + "\"\n\t,\"amount\":\"" + merchant.getAmount() + "\"\n\t,\"merchant_id\":\"" + merchant.getMerchant_id() + "\"\n\t,\"pay_opt_type\":\"\"\n\t,\"settingPromotions\":\"Y\"\n\t,\"cardType\":\"\"\n\t,\"cardName\":\"\"\n\t,\"cardNumber\":\"" + this.creditcard.getText().toString() + "\"\n\t,\"customerIdVault\":\"\"\n\t,\"emailId\":\"" + billing.getEmail() + "\"\n}";
/*      */     
/* 1450 */     Call<String> call = service.post_apply(body);
/*      */     
/* 1452 */     call.enqueue(new Callback<String>()
/*      */         {
/*      */           public void onResponse(Call<String> call, retrofit2.Response<String> response)
/*      */           {
/*      */             try {
/* 1457 */               LoadingDialog.cancelLoading();
/*      */               
/* 1459 */               if (response.body() != null) {
/*      */                 
/* 1461 */                 PaymentOptions.this.validate_promo((String)response.body());
/*      */               } else {
/* 1463 */                 Toast.makeText((Context)PaymentOptions.this, "No Response. Please try Again!!!", 1).show();
/*      */               
/*      */               }
/*      */             
/*      */             }
/* 1468 */             catch (Exception e) {
/*      */               
/* 1470 */               Toast.makeText((Context)PaymentOptions.this, "Error. Please try Again!!!", 1).show();
/* 1471 */               LoadingDialog.cancelLoading();
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void onFailure(Call<String> call, Throwable t) {
/* 1479 */             if (t instanceof java.net.SocketTimeoutException) {
/* 1480 */               LoadingDialog.cancelLoading();
/* 1481 */               Toast.makeText((Context)PaymentOptions.this, "Something went wrong. Please try Again!!!", 1).show();
/*      */             } else {
/*      */               
/* 1484 */               LoadingDialog.cancelLoading();
/* 1485 */               Toast.makeText((Context)PaymentOptions.this, "Something went wrong. Please try Again!!!", 1).show();
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void apply_promo_saved() {
/* 1498 */     LoadingDialog.showLoadingDialog((Context)this, "Loading....");
/* 1499 */     RetrofitClient.GitApiInterface service = RetrofitClient.Indianrail();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1515 */     String body = "{\n\t\"promoCode\":\"" + ((Promotion)promolist.get(pos - 1)).getPromoId() + "\"\n\t,\"currency\":\"" + merchant.getCurrency() + "\"\n\t,\"amount\":\"" + merchant.getAmount() + "\"\n\t,\"merchant_id\":\"" + merchant.getMerchant_id() + "\"\n\t,\"pay_opt_type\":\"" + ((SaveCard)this.SavedCardList.get(this.viewPager.getCurrentItem())).getPayOption() + "\"\n\t,\"settingPromotions\":\"Y\"\n\t,\"cardType\":\"" + ((SaveCard)this.SavedCardList.get(this.viewPager.getCurrentItem())).getPayCardType() + "\"\n\t,\"cardName\":\"" + ((SaveCard)this.SavedCardList.get(this.viewPager.getCurrentItem())).getPayCardName() + "\"\n\t,\"cardNumber\":\"" + ((SaveCard)this.SavedCardList.get(this.viewPager.getCurrentItem())).getPayCardNo() + "\"\n\t,\"customerIdVault\":\"" + merchant.getCustomer_id() + "\"\n\t,\"emailId\":\"" + billing.getEmail() + "\"\n}";
/*      */     
/* 1517 */     Call<String> call = service.post_apply(body);
/*      */     
/* 1519 */     call.enqueue(new Callback<String>()
/*      */         {
/*      */           public void onResponse(Call<String> call, retrofit2.Response<String> response)
/*      */           {
/*      */             try {
/* 1524 */               LoadingDialog.cancelLoading();
/*      */               
/* 1526 */               if (response.body() != null) {
/*      */                 
/* 1528 */                 PaymentOptions.this.validate_promo((String)response.body());
/*      */               } else {
/* 1530 */                 Toast.makeText((Context)PaymentOptions.this, "No Response. Please try Again!!!", 1).show();
/*      */               
/*      */               }
/*      */             
/*      */             }
/* 1535 */             catch (Exception e) {
/*      */               
/* 1537 */               Toast.makeText((Context)PaymentOptions.this, "Error. Please try Again!!!", 1).show();
/* 1538 */               LoadingDialog.cancelLoading();
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void onFailure(Call<String> call, Throwable t) {
/* 1546 */             if (t instanceof java.net.SocketTimeoutException) {
/* 1547 */               LoadingDialog.cancelLoading();
/* 1548 */               Toast.makeText((Context)PaymentOptions.this, "Something went wrong. Please try Again!!!", 1).show();
/*      */             } else {
/*      */               
/* 1551 */               LoadingDialog.cancelLoading();
/* 1552 */               Toast.makeText((Context)PaymentOptions.this, "Something went wrong. Please try Again!!!", 1).show();
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void validate_promo(String resp) {
/* 1564 */     if (resp.equalsIgnoreCase("remove")) {
/* 1565 */       remove_promo();
/*      */     } else {
/*      */       try {
/* 1568 */         JSONObject j = new JSONObject(resp);
/* 1569 */         if (j.getString("isValidPromo").equalsIgnoreCase("Y")) {
/*      */           
/* 1571 */           this.promo_txt.setVisibility(0);
/* 1572 */           this.amt = j.getString("netAmt");
/*      */ 
/*      */           
/* 1575 */           if (((Promotion)promolist.get(this.promolist_spinner.getSelectedItemPosition() - 1)).getPromoType().equalsIgnoreCase("CASHBACK")) {
/* 1576 */             this.promo_txt.setText("Promotion applied\nCashback of " + merchant.getCurrency() + " " + j.getString("promoCashBack") + " applies");
/*      */           } else {
/* 1578 */             this.promo_txt.setText("Promotion applied");
/*      */           } 
/* 1580 */           this.promo_success = "yes";
/* 1581 */           this.promo_txt.setTextColor(Color.parseColor("#006400"));
/* 1582 */           this.pay_card.setText("PAY " + j.getString("netAmt") + " " + merchant.currency);
/* 1583 */           this.tv_inr.setText(merchant.currency + " " + j.getString("netAmt"));
/* 1584 */         } else if (j.getString("isValidPromo").equalsIgnoreCase("N")) {
/* 1585 */           this.promo_txt.setVisibility(0);
/*      */           
/* 1587 */           this.promo_success = "no";
/* 1588 */           if (j.has("errordesc") && j.getString("errordesc").length() > 0) {
/* 1589 */             this.promo_txt.setText(j.getString("errordesc"));
/*      */           } else {
/* 1591 */             this.promo_txt.setText("Promotion not applied on selected card");
/* 1592 */           }  this.promo_txt.setTextColor(-65536);
/* 1593 */           this.pay_card.setText("PAY " + merchant.getAmount() + " " + merchant.currency);
/* 1594 */           this.tv_inr.setText(merchant.currency + " " + merchant.amount);
/*      */         } 
/* 1596 */       } catch (JSONException e) {
/* 1597 */         e.printStackTrace();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void remove_promo() {
/* 1605 */     this.promo_success = "";
/* 1606 */     this.promo_txt.setText("");
/* 1607 */     this.promo_txt.setVisibility(8);
/* 1608 */     this.pay_card.setText("PAY " + merchant.getAmount() + " " + merchant.currency);
/* 1609 */     this.tv_inr.setText(merchant.currency + " " + merchant.amount);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void apply_coupon() {
/* 1617 */     LoadingDialog.showLoadingDialog((Context)this, "Loading....");
/* 1618 */     RetrofitClient.GitApiInterface service = RetrofitClient.Indianrail();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1628 */     String body = "{\n\t\"currency\":\"" + merchant.getCurrency() + "\"\n\t,\"amount\":\"" + merchant.getAmount() + "\"\n\t,\"merchant_id\":\"" + merchant.getMerchant_id() + "\"\n\t,\"couponCode\":\"" + this.edt_coupon.getText().toString() + "\"\n\t,\"emailId\":\"" + billing.getEmail() + "\"\n}";
/*      */     
/* 1630 */     Call<String> call = service.post_apply_coupon(body);
/*      */     
/* 1632 */     call.enqueue(new Callback<String>()
/*      */         {
/*      */           public void onResponse(Call<String> call, retrofit2.Response<String> response)
/*      */           {
/*      */             try {
/* 1637 */               LoadingDialog.cancelLoading();
/*      */               
/* 1639 */               if (response.body() != null)
/*      */               {
/* 1641 */                 JSONObject j = new JSONObject((String)response.body());
/* 1642 */                 String isvalid = j.getString("isValidDiscount");
/* 1643 */                 if (isvalid.equalsIgnoreCase("Y")) {
/* 1644 */                   if (PaymentOptions.this.promolist_spinner.getSelectedItemPosition() > 0) {
/* 1645 */                     PaymentOptions.this.promolist_spinner.setSelection(0);
/* 1646 */                     PaymentOptions.this.remove_promo();
/*      */                   } 
/* 1648 */                   PaymentOptions.this.coupon_txt.setVisibility(0);
/* 1649 */                   PaymentOptions.this.apply.setText("Remove");
/* 1650 */                   PaymentOptions.this.coupon_txt.setText("Coupon applied");
/* 1651 */                   PaymentOptions.this.edt_coupon.setEnabled(false);
/* 1652 */                   PaymentOptions.this.coupon_txt.setTextColor(-16711936);
/* 1653 */                   PaymentOptions.this.pay_card.setText("PAY " + j.getString("netAmt") + " " + PaymentOptions.merchant.currency);
/* 1654 */                   PaymentOptions.this.tv_inr.setText(PaymentOptions.merchant.currency + " " + j.getString("netAmt"));
/*      */                 }
/*      */                 else {
/*      */                   
/* 1658 */                   if (PaymentOptions.this.promolist_spinner.getSelectedItemPosition() > 0) {
/* 1659 */                     PaymentOptions.this.promolist_spinner.setSelection(0);
/* 1660 */                     PaymentOptions.this.remove_promo();
/*      */                   } 
/* 1662 */                   PaymentOptions.this.coupon_txt.setVisibility(0);
/*      */                   
/* 1664 */                   PaymentOptions.this.coupon_txt.setText(j.getString("errorDesc"));
/* 1665 */                   PaymentOptions.this.coupon_txt.setTextColor(-65536);
/* 1666 */                   PaymentOptions.this.pay_card.setText("PAY " + j.getString("netAmt") + " " + PaymentOptions.merchant.currency);
/* 1667 */                   PaymentOptions.this.tv_inr.setText(PaymentOptions.merchant.currency + " " + j.getString("netAmt"));
/*      */                 } 
/*      */ 
/*      */                 
/* 1671 */                 int a = 1;
/* 1672 */                 a += 2;
/*      */               }
/*      */               else
/*      */               {
/* 1676 */                 Toast.makeText((Context)PaymentOptions.this, "No Response. Please try Again!!!", 1).show();
/*      */               
/*      */               }
/*      */             
/*      */             }
/* 1681 */             catch (Exception e) {
/*      */               
/* 1683 */               Toast.makeText((Context)PaymentOptions.this, "Error. Please try Again!!!", 1).show();
/* 1684 */               LoadingDialog.cancelLoading();
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void onFailure(Call<String> call, Throwable t) {
/* 1692 */             if (t instanceof java.net.SocketTimeoutException) {
/* 1693 */               LoadingDialog.cancelLoading();
/* 1694 */               Toast.makeText((Context)PaymentOptions.this, "Something went wrong. Please try Again!!!", 1).show();
/*      */             } else {
/*      */               
/* 1697 */               LoadingDialog.cancelLoading();
/* 1698 */               Toast.makeText((Context)PaymentOptions.this, "Something went wrong. Please try Again!!!", 1).show();
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void remove_coupon() {
/* 1711 */     this.coupon_txt.setText("");
/* 1712 */     this.edt_coupon.setText("");
/* 1713 */     this.edt_coupon.setEnabled(true);
/* 1714 */     this.apply.setText("Apply");
/* 1715 */     this.coupon_txt.setVisibility(8);
/* 1716 */     this.pay_card.setText("PAY " + merchant.getAmount() + " " + merchant.currency);
/* 1717 */     this.tv_inr.setText(merchant.currency + " " + merchant.amount);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setInputTextLayoutColor(TextInputLayout textInputLayout, int color) {
/*      */     try {
/* 1723 */       Field field = textInputLayout.getClass().getDeclaredField("mFocusedTextColor");
/* 1724 */       field.setAccessible(true);
/* 1725 */       int[][] states = { {} };
/*      */ 
/*      */       
/* 1728 */       int[] colors = { color };
/*      */ 
/*      */       
/* 1731 */       ColorStateList myList = new ColorStateList(states, colors);
/* 1732 */       field.set(textInputLayout, myList);
/*      */ 
/*      */       
/* 1735 */       Method method = textInputLayout.getClass().getDeclaredMethod("updateLabelState", new Class[] { boolean.class });
/* 1736 */       method.setAccessible(true);
/* 1737 */       method.invoke(textInputLayout, new Object[] { Boolean.valueOf(true) });
/*      */     }
/* 1739 */     catch (Exception e) {
/* 1740 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void getcolor() {
/* 1746 */     LoadingDialog.showLoadingDialog((Context)this, "Loading....");
/* 1747 */     RetrofitClient.GitApiInterface1 service = RetrofitClient.Indianrail1();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1753 */     String body = " {\n\"reg_id\": " + merchant.getMerchant_id() + "\n}\n";
/*      */     
/* 1755 */     Call<String> call = service.get_color(body);
/*      */     
/* 1757 */     call.enqueue(new Callback<String>()
/*      */         {
/*      */ 
/*      */           
/*      */           public void onResponse(Call<String> call, retrofit2.Response<String> response)
/*      */           {
/*      */             try {
/* 1764 */               if (response.body() != null) {
/*      */                 
/* 1766 */                 JSONObject j = new JSONObject((String)response.body());
/* 1767 */                 PaymentOptions.this.color_text = j.getString("activeTabTxt");
/* 1768 */                 PaymentOptions.this.color_btn = j.getString("buttonBkg");
/* 1769 */                 PaymentOptions.this.color_toolbar = j.getString("headerBottomBkg");
/* 1770 */                 PaymentOptions.this.color_grad_min = j.getString("headerBottomBkg");
/* 1771 */                 PaymentOptions.this.headerDivider = j.getString("headerDivider");
/* 1772 */                 PaymentOptions.this.headerTopBkg = j.getString("headerTopBkg");
/* 1773 */                 PaymentOptions.this.headerTxt = j.getString("headerTxt");
/* 1774 */                 PaymentOptions.this.buttonTxt = j.getString("buttonTxt");
/* 1775 */                 PaymentOptions.this.bodyTxt = j.getString("bodyTxt");
/* 1776 */                 PaymentOptions.this.inactiveTabTxt = j.getString("inactiveTabTxt");
/* 1777 */                 PaymentOptions.this.inactiveTabBkg = j.getString("inactiveTabBkg");
/* 1778 */                 PaymentOptions.this.set();
/*      */               } else {
/*      */                 
/* 1781 */                 Toast.makeText((Context)PaymentOptions.this, "No Response. Please try Again!!!", 1).show();
/*      */               
/*      */               }
/*      */             
/*      */             }
/* 1786 */             catch (Exception e) {
/*      */               
/* 1788 */               Toast.makeText((Context)PaymentOptions.this, "Error. Please try Again!!!", 1).show();
/* 1789 */               LoadingDialog.cancelLoading();
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void onFailure(Call<String> call, Throwable t) {
/* 1797 */             if (t instanceof java.net.SocketTimeoutException) {
/* 1798 */               LoadingDialog.cancelLoading();
/* 1799 */               Toast.makeText((Context)PaymentOptions.this, "Something went wrong. Please try Again!!!", 1).show();
/*      */             } else {
/*      */               
/* 1802 */               LoadingDialog.cancelLoading();
/* 1803 */               Toast.makeText((Context)PaymentOptions.this, "Something went wrong. Please try Again!!!", 1).show();
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @SuppressLint({"RestrictedApi"})
/*      */   public void set() {
/* 1817 */     initialiseToolbar("Payment Details");
/* 1818 */     this.header_divider = findViewById(R.id.header_divider);
/* 1819 */     this.checkBox1 = (CheckBox)findViewById(R.id.checkBox1);
/* 1820 */     this.payother = (TextView)findViewById(R.id.payother);
/* 1821 */     this.savedcard = (TextView)findViewById(R.id.savedcard);
/*      */ 
/*      */     
/* 1824 */     this.edit = (TextView)findViewById(R.id.edit);
/* 1825 */     this.edit_email = (TextInputLayout)findViewById(R.id.tl_email);
/* 1826 */     this.ll_main_edit = (LinearLayout)findViewById(R.id.ll_main_edit);
/* 1827 */     this.ll_main = (LinearLayout)findViewById(R.id.ll_main);
/* 1828 */     this.ll_billing_address = (LinearLayout)findViewById(R.id.ll_billing_address);
/* 1829 */     this.ll_main_billing = (LinearLayout)findViewById(R.id.ll_main_billing);
/* 1830 */     this.ll_saved_card = (LinearLayout)findViewById(R.id.ll_saved_card);
/* 1831 */     this.ll_promo = (LinearLayout)findViewById(R.id.ll_promo);
/* 1832 */     this.ll_new_card = (LinearLayout)findViewById(R.id.ll_new_card);
/*      */     
/* 1834 */     this.head = (LinearLayout)findViewById(R.id.head);
/* 1835 */     this.head_edit = (LinearLayout)findViewById(R.id.head_edit);
/* 1836 */     this.ll_coupon = (LinearLayout)findViewById(R.id.ll_coupon);
/* 1837 */     this.billing_edit_head = (TextView)findViewById(R.id.billing_edit_head);
/* 1838 */     this.txt_billing = (TextView)findViewById(R.id.billing);
/* 1839 */     this.billing_name = (TextView)findViewById(R.id.billing_name);
/* 1840 */     this.billing_add1 = (TextView)findViewById(R.id.billing_add1);
/* 1841 */     this.billing_add2 = (TextView)findViewById(R.id.billing_add2);
/* 1842 */     this.billing_mob = (TextView)findViewById(R.id.billing_mob);
/* 1843 */     this.billing_email = (TextView)findViewById(R.id.billing_email);
/* 1844 */     this.txt_shipping = (TextView)findViewById(R.id.shipping);
/* 1845 */     this.txt_promo = (TextView)findViewById(R.id.promo);
/* 1846 */     this.promo_txt = (TextView)findViewById(R.id.promo_txt);
/* 1847 */     this.coupon_txt = (TextView)findViewById(R.id.coupon_txt);
/* 1848 */     this.promolist_spinner = (AppCompatSpinner)findViewById(R.id.promolist);
/* 1849 */     this.name_edit = (EditText)findViewById(R.id.name_edit);
/* 1850 */     this.address_edit = (EditText)findViewById(R.id.address_edit);
/* 1851 */     this.city_edit = (EditText)findViewById(R.id.city_edit);
/* 1852 */     this.state_edit = (EditText)findViewById(R.id.state_edit);
/* 1853 */     this.country_edit = (EditText)findViewById(R.id.country_edit);
/* 1854 */     this.phonenumber_edit = (EditText)findViewById(R.id.phonenumber_edit);
/* 1855 */     this.email_edit = (EditText)findViewById(R.id.email_edit);
/*      */ 
/*      */     
/* 1858 */     this.mkpay = (TextView)findViewById(R.id.mkpay);
/* 1859 */     this.mkpay1 = (TextView)findViewById(R.id.mkpay1);
/*      */     
/* 1861 */     this.tv_inr = (TextView)findViewById(R.id.tv_inr);
/* 1862 */     this.pay_card = (Button)findViewById(R.id.pay_card);
/*      */     
/* 1864 */     this.apply = (Button)findViewById(R.id.apply);
/*      */ 
/*      */     
/* 1867 */     this.ll_mkpay = (LinearLayout)findViewById(R.id.ll_mkpay);
/* 1868 */     this.ll_mkpay1 = (LinearLayout)findViewById(R.id.ll_mkpay1);
/*      */     
/* 1870 */     this.creditcard = (CreditCardEditText)findViewById(R.id.creditcard);
/* 1871 */     this.edt_coupon = (EditText)findViewById(R.id.edt_coupon);
/*      */     
/* 1873 */     this.ip_cvv = (EditText)findViewById(R.id.ip_cvv);
/* 1874 */     this.ip_exp = (EditText)findViewById(R.id.ip_exp);
/* 1875 */     this.tv_orderid = (TextView)findViewById(R.id.tv_orderid);
/* 1876 */     this.tv_inr.setText(merchant.currency + " " + merchant.amount);
/* 1877 */     this.tv_orderid.setText("Order #: " + merchant.order_id);
/*      */ 
/*      */     
/* 1880 */     this.expandableLayout0 = (ExpandableLayout)findViewById(R.id.expandable_layout_01);
/*      */     
/* 1882 */     this.expand = (ImageView)findViewById(R.id.expand);
/* 1883 */     this.pay_card.setText("PAY " + merchant.amount + " " + merchant.currency);
/*      */     
/* 1885 */     this.expand.setOnClickListener(this);
/* 1886 */     this.pay_card.setOnClickListener(this);
/* 1887 */     this.apply.setOnClickListener(this);
/* 1888 */     this.payother.setOnClickListener(this);
/* 1889 */     this.savedcard.setOnClickListener(this);
/*      */     
/* 1891 */     this.promolist_spinner.setSelection(0);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1896 */     this.promo_txt.setVisibility(8);
/* 1897 */     this.promo_txt.setText("");
/*      */ 
/*      */     
/* 1900 */     if (!standardInstructions.getSi_type().equals("")) {
/* 1901 */       if (standardInstructions.getSi_type().equals("FIXED")) {
/* 1902 */         this.checkBox1.setChecked(true);
/* 1903 */         this.checkBox1.setText("I hereby authorize CCAvenue to save my card for future recurring transactions and my convenience.");
/* 1904 */         this.checkBox1.setEnabled(false);
/* 1905 */         this.checkBox1.setTextSize(12.0F);
/* 1906 */       } else if (standardInstructions.getSi_type().equals("ONDEMAND")) {
/* 1907 */         this.checkBox1.setChecked(true);
/* 1908 */         this.checkBox1.setText("I hereby authorize CCAvenue to save my card for future recurring transactions and my convenience.");
/* 1909 */         this.checkBox1.setEnabled(false);
/* 1910 */         this.checkBox1.setTextSize(12.0F);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 1915 */     this.promolist_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
/*      */         {
/*      */           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
/* 1918 */             PaymentOptions.pos = position;
/* 1919 */             PaymentOptions.this.remove_promo();
/*      */             
/* 1921 */             if (position == 0 && 
/* 1922 */               Constants.DISCOUNT.equalsIgnoreCase("Y")) {
/* 1923 */               PaymentOptions.this.ll_coupon.setVisibility(0);
/*      */             }
/* 1925 */             if (position > 0) {
/*      */ 
/*      */               
/* 1928 */               PaymentOptions.pos = position;
/*      */ 
/*      */               
/* 1931 */               if (PaymentOptions.merchant.getPromo_code().length() > 0) {
/* 1932 */                 PaymentOptions.this.promolist_spinner.setEnabled(false);
/* 1933 */                 if (PaymentOptions.this.ll_new_card.getVisibility() == 0) {
/* 1934 */                   if (PaymentOptions.this.creditcard.getText().length() == 16) {
/* 1935 */                     PaymentOptions.this.apply_promo();
/*      */                   }
/* 1937 */                 } else if (PaymentOptions.this.ll_saved_card.getVisibility() == 0) {
/* 1938 */                   PaymentOptions.this.apply_promo_saved();
/*      */                 } 
/*      */               } else {
/*      */                 
/* 1942 */                 AlertDialog alertDialog = (new AlertDialog.Builder(PaymentOptions.this.mContext)).create();
/*      */                 
/* 1944 */                 alertDialog.setTitle(((Promotion)PaymentOptions.promolist.get(position - 1)).getPromoName());
/*      */ 
/*      */                 
/* 1947 */                 alertDialog.setMessage(((Promotion)PaymentOptions.promolist.get(position - 1)).getPromotionDesc() + "\n\n" + ((Promotion)PaymentOptions.promolist.get(position - 1)).getPromoTandC());
/*      */ 
/*      */                 
/* 1950 */                 alertDialog.setButton(-1, "USE", new DialogInterface.OnClickListener()
/*      */                     {
/*      */ 
/*      */                       
/*      */                       public void onClick(DialogInterface dialog, int which)
/*      */                       {
/* 1956 */                         PaymentOptions.this.remove_coupon();
/* 1957 */                         PaymentOptions.this.ll_coupon.setVisibility(8);
/* 1958 */                         if (PaymentOptions.this.ll_new_card.getVisibility() == 0) {
/* 1959 */                           if (PaymentOptions.this.creditcard.getText().length() == 16) {
/* 1960 */                             PaymentOptions.this.apply_promo();
/*      */                           }
/* 1962 */                         } else if (PaymentOptions.this.ll_saved_card.getVisibility() == 0) {
/* 1963 */                           PaymentOptions.this.apply_promo_saved();
/*      */                         } 
/*      */                       }
/*      */                     });
/*      */ 
/*      */ 
/*      */                 
/* 1970 */                 alertDialog.setButton(-2, "CANCEL", new DialogInterface.OnClickListener()
/*      */                     {
/*      */                       public void onClick(DialogInterface dialog, int which)
/*      */                       {
/* 1974 */                         PaymentOptions.this.promolist_spinner.setSelection(0);
/*      */                       }
/*      */                     });
/*      */ 
/*      */ 
/*      */                 
/* 1980 */                 alertDialog.show();
/*      */               } 
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void onNothingSelected(AdapterView<?> parent) {}
/*      */         });
/* 1997 */     this.ip_exp.addTextChangedListener(new TwoDigitsCardTextWatcher(this.ip_exp));
/*      */ 
/*      */     
/* 2000 */     this.expandableLayout0.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener()
/*      */         {
/*      */           public void onExpansionUpdate(float expansionFraction, int state)
/*      */           {
/* 2004 */             if (state == 3) {
/* 2005 */               if (Build.VERSION.SDK_INT >= 21) {
/* 2006 */                 PaymentOptions.this.expand.setImageDrawable(PaymentOptions.this.getResources().getDrawable(R.drawable.up, PaymentOptions.this.getApplicationContext().getTheme()));
/*      */               } else {
/* 2008 */                 PaymentOptions.this.expand.setImageDrawable(PaymentOptions.this.getResources().getDrawable(R.drawable.up));
/*      */               } 
/* 2010 */             } else if (state == 0) {
/* 2011 */               if (Build.VERSION.SDK_INT >= 21) {
/* 2012 */                 PaymentOptions.this.expand.setImageDrawable(PaymentOptions.this.getResources().getDrawable(R.drawable.down, PaymentOptions.this.getApplicationContext().getTheme()));
/*      */               } else {
/* 2014 */                 PaymentOptions.this.expand.setImageDrawable(PaymentOptions.this.getResources().getDrawable(R.drawable.down));
/*      */               } 
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */     
/* 2021 */     if (!merchant.isShow_addr()) {
/* 2022 */       this.ll_main_billing.setVisibility(8);
/*      */     } else {
/*      */       
/* 2025 */       this.ll_main_billing.setVisibility(0);
/* 2026 */       this.billing_name.setText(billing.getName());
/* 2027 */       this.billing_add1.setText(billing.getAddress());
/* 2028 */       this.billing_add2.setText(billing.getCity() + " " + billing.getState() + " " + billing.getCountry());
/* 2029 */       this.billing_mob.setText(billing.getTelephone());
/* 2030 */       this.billing_email.setText(billing.getEmail());
/* 2031 */       this.txt_billing.setVisibility(0);
/* 2032 */       this.txt_shipping.setVisibility(0);
/* 2033 */       this.txt_billing.setTextColor(Color.parseColor(this.color_text));
/* 2034 */       this.txt_billing.setBackgroundColor(getResources().getColor(R.color.white));
/*      */       
/* 2036 */       this.txt_shipping.setTextColor(Color.parseColor(this.inactiveTabTxt));
/* 2037 */       this.txt_shipping.setBackgroundColor(Color.parseColor(this.inactiveTabBkg));
/*      */     } 
/*      */     
/* 2040 */     this.txt_billing.setOnClickListener(new View.OnClickListener()
/*      */         {
/*      */           public void onClick(View v) {
/* 2043 */             PaymentOptions.this.selected_billing = "billing";
/*      */ 
/*      */ 
/*      */             
/* 2047 */             PaymentOptions.this.ll_promo.setVisibility(8);
/* 2048 */             PaymentOptions.this.ll_billing_address.setVisibility(0);
/* 2049 */             PaymentOptions.this.billing_name.setText(PaymentOptions.billing.getName());
/* 2050 */             PaymentOptions.this.billing_add1.setText(PaymentOptions.billing.getAddress());
/* 2051 */             PaymentOptions.this.billing_add2.setText(PaymentOptions.billing.getCity() + " " + PaymentOptions.billing.getState() + " " + PaymentOptions.billing.getCountry());
/* 2052 */             PaymentOptions.this.billing_mob.setText(PaymentOptions.billing.getTelephone());
/* 2053 */             PaymentOptions.this.billing_email.setVisibility(0);
/* 2054 */             PaymentOptions.this.billing_email.setText(PaymentOptions.billing.getEmail());
/* 2055 */             PaymentOptions.this.txt_billing.setVisibility(0);
/* 2056 */             PaymentOptions.this.txt_shipping.setVisibility(0);
/* 2057 */             PaymentOptions.this.txt_billing.setTextColor(Color.parseColor(PaymentOptions.this.color_text));
/* 2058 */             PaymentOptions.this.txt_billing.setBackgroundColor(PaymentOptions.this.getResources().getColor(R.color.white));
/*      */             
/* 2060 */             PaymentOptions.this.txt_shipping.setTextColor(Color.parseColor(PaymentOptions.this.inactiveTabTxt));
/* 2061 */             PaymentOptions.this.txt_shipping.setBackgroundColor(Color.parseColor(PaymentOptions.this.inactiveTabBkg));
/*      */             
/* 2063 */             PaymentOptions.this.txt_promo.setTextColor(Color.parseColor(PaymentOptions.this.inactiveTabTxt));
/* 2064 */             PaymentOptions.this.txt_promo.setBackgroundColor(Color.parseColor(PaymentOptions.this.inactiveTabBkg));
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2072 */     this.txt_shipping.setOnClickListener(new View.OnClickListener()
/*      */         {
/*      */           public void onClick(View v) {
/* 2075 */             PaymentOptions.this.selected_billing = "shipping";
/*      */ 
/*      */             
/* 2078 */             PaymentOptions.this.ll_promo.setVisibility(8);
/* 2079 */             PaymentOptions.this.ll_billing_address.setVisibility(0);
/* 2080 */             PaymentOptions.this.billing_name.setText(PaymentOptions.shipping.getName());
/* 2081 */             PaymentOptions.this.billing_add1.setText(PaymentOptions.shipping.getAddress());
/* 2082 */             PaymentOptions.this.billing_add2.setText(PaymentOptions.shipping.getCity() + " " + PaymentOptions.shipping.getState() + " " + PaymentOptions.shipping.getCountry());
/* 2083 */             PaymentOptions.this.billing_mob.setText(PaymentOptions.shipping.getTelephone());
/* 2084 */             PaymentOptions.this.billing_email.setVisibility(8);
/* 2085 */             PaymentOptions.this.txt_billing.setVisibility(0);
/* 2086 */             PaymentOptions.this.txt_shipping.setVisibility(0);
/* 2087 */             PaymentOptions.this.txt_shipping.setTextColor(Color.parseColor(PaymentOptions.this.color_text));
/* 2088 */             PaymentOptions.this.txt_shipping.setBackgroundColor(PaymentOptions.this.getResources().getColor(R.color.white));
/*      */             
/* 2090 */             PaymentOptions.this.txt_billing.setTextColor(Color.parseColor(PaymentOptions.this.inactiveTabTxt));
/* 2091 */             PaymentOptions.this.txt_billing.setBackgroundColor(Color.parseColor(PaymentOptions.this.inactiveTabBkg));
/*      */             
/* 2093 */             PaymentOptions.this.txt_promo.setTextColor(Color.parseColor(PaymentOptions.this.inactiveTabTxt));
/* 2094 */             PaymentOptions.this.txt_promo.setBackgroundColor(Color.parseColor(PaymentOptions.this.inactiveTabBkg));
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2102 */     this.edit.setOnClickListener(new View.OnClickListener()
/*      */         {
/*      */           public void onClick(View v)
/*      */           {
/* 2106 */             PaymentOptions.this.flg_edit = true;
/*      */             
/* 2108 */             PaymentOptions.this.pay_card.setText("UPDATE");
/*      */             
/* 2110 */             PaymentOptions.this.ll_main.setVisibility(8);
/* 2111 */             PaymentOptions.this.ll_main_edit.setVisibility(0);
/*      */             
/* 2113 */             if (PaymentOptions.this.selected_billing.equalsIgnoreCase("billing")) {
/*      */               
/* 2115 */               PaymentOptions.this.billing_edit_head.setText("Edit Billing");
/* 2116 */               PaymentOptions.this.name_edit.setText(PaymentOptions.billing.getName());
/* 2117 */               PaymentOptions.this.address_edit.setText(PaymentOptions.billing.getAddress());
/* 2118 */               PaymentOptions.this.city_edit.setText(PaymentOptions.billing.getCity());
/* 2119 */               PaymentOptions.this.state_edit.setText(PaymentOptions.billing.getState());
/* 2120 */               PaymentOptions.this.country_edit.setText(PaymentOptions.billing.getCountry());
/*      */               
/* 2122 */               PaymentOptions.this.phonenumber_edit.setText(PaymentOptions.billing.getTelephone());
/* 2123 */               PaymentOptions.this.edit_email.setVisibility(0);
/* 2124 */               PaymentOptions.this.email_edit.setText(PaymentOptions.billing.getEmail());
/*      */             } else {
/* 2126 */               PaymentOptions.this.billing_edit_head.setText("Edit Shipping");
/* 2127 */               PaymentOptions.this.name_edit.setText(PaymentOptions.shipping.getName());
/* 2128 */               PaymentOptions.this.address_edit.setText(PaymentOptions.shipping.getAddress());
/* 2129 */               PaymentOptions.this.city_edit.setText(PaymentOptions.shipping.getCity());
/* 2130 */               PaymentOptions.this.state_edit.setText(PaymentOptions.billing.getState());
/* 2131 */               PaymentOptions.this.country_edit.setText(PaymentOptions.shipping.getCountry());
/*      */               
/* 2133 */               PaymentOptions.this.phonenumber_edit.setText(PaymentOptions.shipping.getTelephone());
/* 2134 */               PaymentOptions.this.edit_email.setVisibility(8);
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2148 */     TextView edit = (TextView)findViewById(R.id.edit);
/* 2149 */     TextView head_accept = (TextView)findViewById(R.id.head_accept);
/*      */     
/* 2151 */     this.mToolbar.setBackgroundColor(Color.parseColor(this.headerTopBkg));
/* 2152 */     this.mToolbar.setTitleTextColor(Color.parseColor("#000000"));
/*      */     
/* 2154 */     this.mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
/*      */     
/* 2156 */     edit.setTextColor(Color.parseColor(this.color_text));
/*      */     
/* 2158 */     this.header_divider.setBackgroundColor(Color.parseColor(this.headerDivider));
/*      */     
/* 2160 */     this.promo_txt.setTextColor(Color.parseColor(this.color_text));
/* 2161 */     this.payother.setTextColor(Color.parseColor(this.color_text));
/* 2162 */     this.savedcard.setTextColor(Color.parseColor(this.color_text));
/* 2163 */     head_accept.setTextColor(Color.parseColor(this.color_text));
/* 2164 */     this.checkBox1.setTextColor(Color.parseColor(this.color_text));
/* 2165 */     if (Build.VERSION.SDK_INT < 21) {
/* 2166 */       CompoundButtonCompat.setButtonTintList((CompoundButton)this.checkBox1, ColorStateList.valueOf(Color.parseColor(this.color_text)));
/*      */     } else {
/* 2168 */       this.checkBox1.setButtonTintList(ColorStateList.valueOf(Color.parseColor(this.color_text)));
/*      */     } 
/* 2170 */     this.billing_edit_head.setTextColor(Color.parseColor(this.color_text));
/* 2171 */     this.head_edit.setBackgroundColor(Color.parseColor(this.inactiveTabBkg));
/*      */ 
/*      */     
/* 2174 */     GradientDrawable bgShape = (GradientDrawable)this.pay_card.getBackground();
/* 2175 */     bgShape.setColor(Color.parseColor(this.color_btn));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2182 */     GradientDrawable bgShape_apply = (GradientDrawable)this.apply.getBackground();
/* 2183 */     bgShape_apply.setColor(Color.parseColor(this.color_btn));
/*      */ 
/*      */     
/* 2186 */     TextInputLayout exp = (TextInputLayout)findViewById(R.id.exp);
/* 2187 */     TextInputLayout cvv = (TextInputLayout)findViewById(R.id.cvv);
/* 2188 */     TextInputLayout cred = (TextInputLayout)findViewById(R.id.cred);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2196 */     int[] colors = { Color.parseColor(this.color_toolbar), Color.parseColor(this.color_grad_min) };
/*      */     
/* 2198 */     GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
/*      */     
/* 2200 */     gradientDrawable.setShape(0);
/* 2201 */     LinearLayout top = (LinearLayout)findViewById(R.id.top);
/* 2202 */     top.setBackground((Drawable)gradientDrawable);
/* 2203 */     this.pay_card.setTextColor(Color.parseColor(this.buttonTxt));
/* 2204 */     this.billing_name.setTextColor(Color.parseColor(this.bodyTxt));
/* 2205 */     this.billing_add1.setTextColor(Color.parseColor(this.bodyTxt));
/* 2206 */     this.billing_add2.setTextColor(Color.parseColor(this.bodyTxt));
/* 2207 */     this.billing_mob.setTextColor(Color.parseColor(this.bodyTxt));
/* 2208 */     this.billing_email.setTextColor(Color.parseColor(this.bodyTxt));
/* 2209 */     this.mkpay.setTextColor(Color.parseColor(this.inactiveTabTxt));
/* 2210 */     this.mkpay1.setTextColor(Color.parseColor(this.inactiveTabTxt));
/* 2211 */     this.ll_mkpay.setBackgroundColor(Color.parseColor(this.inactiveTabBkg));
/* 2212 */     this.ll_mkpay1.setBackgroundColor(Color.parseColor(this.inactiveTabBkg));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2222 */     this.checkBox1.setTextColor(Color.parseColor("#000000"));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2228 */     int[][] states1 = { { -16842908 }, { 16842908 } };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2235 */     int[] thumbColors = { Color.parseColor(this.color_text), Color.parseColor(this.color_text) };
/*      */ 
/*      */ 
/*      */     
/* 2239 */     ColorStateList colorStateList = ColorStateList.valueOf(Color.parseColor(this.color_text));
/*      */     
/* 2241 */     ViewCompat.setBackgroundTintList((View)this.ip_exp, colorStateList);
/* 2242 */     setUpperHintColor(exp, Color.parseColor(this.color_text));
/* 2243 */     setCursorColor(this.ip_exp, Color.parseColor(this.color_text));
/* 2244 */     ((AppCompatEditText)this.ip_exp).setSupportBackgroundTintList(new ColorStateList(states1, thumbColors));
/*      */ 
/*      */     
/* 2247 */     ViewCompat.setBackgroundTintList((View)this.ip_cvv, colorStateList);
/* 2248 */     setUpperHintColor(cvv, Color.parseColor(this.color_text));
/* 2249 */     setCursorColor(this.ip_cvv, Color.parseColor(this.color_text));
/* 2250 */     ((AppCompatEditText)this.ip_cvv).setSupportBackgroundTintList(new ColorStateList(states1, thumbColors));
/*      */ 
/*      */     
/* 2253 */     ViewCompat.setBackgroundTintList((View)this.creditcard, colorStateList);
/* 2254 */     setUpperHintColor(cred, Color.parseColor(this.color_text));
/* 2255 */     setCursorColor((EditText)this.creditcard, Color.parseColor(this.color_text));
/* 2256 */     this.creditcard.setSupportBackgroundTintList(new ColorStateList(states1, thumbColors));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2264 */     TextInputLayout tl_name = (TextInputLayout)findViewById(R.id.tl_name);
/* 2265 */     setUpperHintColor(tl_name, Color.parseColor(this.color_text));
/* 2266 */     ViewCompat.setBackgroundTintList((View)this.name_edit, colorStateList);
/* 2267 */     setCursorColor(this.name_edit, Color.parseColor(this.color_text));
/* 2268 */     ((AppCompatEditText)this.name_edit).setSupportBackgroundTintList(new ColorStateList(states1, thumbColors));
/*      */ 
/*      */     
/* 2271 */     TextInputLayout tl_address = (TextInputLayout)findViewById(R.id.tl_address);
/* 2272 */     setUpperHintColor(tl_address, Color.parseColor(this.color_text));
/* 2273 */     ViewCompat.setBackgroundTintList((View)this.address_edit, colorStateList);
/* 2274 */     setCursorColor(this.address_edit, Color.parseColor(this.color_text));
/* 2275 */     ((AppCompatEditText)this.address_edit).setSupportBackgroundTintList(new ColorStateList(states1, thumbColors));
/*      */ 
/*      */     
/* 2278 */     TextInputLayout tl_city = (TextInputLayout)findViewById(R.id.tl_city);
/* 2279 */     setUpperHintColor(tl_city, Color.parseColor(this.color_text));
/* 2280 */     ViewCompat.setBackgroundTintList((View)this.city_edit, colorStateList);
/* 2281 */     setCursorColor(this.city_edit, Color.parseColor(this.color_text));
/* 2282 */     ((AppCompatEditText)this.city_edit).setSupportBackgroundTintList(new ColorStateList(states1, thumbColors));
/*      */ 
/*      */ 
/*      */     
/* 2286 */     TextInputLayout tl_state = (TextInputLayout)findViewById(R.id.tl_state);
/* 2287 */     setUpperHintColor(tl_state, Color.parseColor(this.color_text));
/* 2288 */     ViewCompat.setBackgroundTintList((View)this.state_edit, colorStateList);
/* 2289 */     setCursorColor(this.state_edit, Color.parseColor(this.color_text));
/* 2290 */     ((AppCompatEditText)this.state_edit).setSupportBackgroundTintList(new ColorStateList(states1, thumbColors));
/*      */ 
/*      */ 
/*      */     
/* 2294 */     TextInputLayout tl_country = (TextInputLayout)findViewById(R.id.tl_country);
/* 2295 */     setUpperHintColor(tl_country, Color.parseColor(this.color_text));
/* 2296 */     ViewCompat.setBackgroundTintList((View)this.country_edit, colorStateList);
/* 2297 */     setCursorColor(this.country_edit, Color.parseColor(this.color_text));
/* 2298 */     ((AppCompatEditText)this.country_edit).setSupportBackgroundTintList(new ColorStateList(states1, thumbColors));
/*      */ 
/*      */ 
/*      */     
/* 2302 */     TextInputLayout tl_phonenumber = (TextInputLayout)findViewById(R.id.tl_phonenumber);
/* 2303 */     setUpperHintColor(tl_phonenumber, Color.parseColor(this.color_text));
/* 2304 */     ViewCompat.setBackgroundTintList((View)this.phonenumber_edit, colorStateList);
/* 2305 */     setCursorColor(this.phonenumber_edit, Color.parseColor(this.color_text));
/* 2306 */     ((AppCompatEditText)this.phonenumber_edit).setSupportBackgroundTintList(new ColorStateList(states1, thumbColors));
/*      */ 
/*      */ 
/*      */     
/* 2310 */     TextInputLayout tl_email = (TextInputLayout)findViewById(R.id.tl_email);
/* 2311 */     setUpperHintColor(tl_email, Color.parseColor(this.color_text));
/* 2312 */     ViewCompat.setBackgroundTintList((View)this.email_edit, colorStateList);
/* 2313 */     setCursorColor(this.email_edit, Color.parseColor(this.color_text));
/* 2314 */     ((AppCompatEditText)this.email_edit).setSupportBackgroundTintList(new ColorStateList(states1, thumbColors));
/*      */     
/* 2316 */     this.tv_inr.setTextColor(Color.parseColor(this.headerTxt));
/* 2317 */     this.tv_orderid.setTextColor(Color.parseColor(this.headerTxt));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2325 */     getSettings();
/*      */   }
/*      */ 
/*      */   
/*      */   private void setUpperHintColor(TextInputLayout textInputLayout, int color) {
/*      */     try {
/* 2331 */       Field field = textInputLayout.getClass().getDeclaredField("mFocusedTextColor");
/* 2332 */       field.setAccessible(true);
/* 2333 */       int[][] states = { {} };
/*      */ 
/*      */       
/* 2336 */       int[] colors = { color };
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2341 */       int[][] states1 = { {} };
/*      */ 
/*      */ 
/*      */       
/* 2345 */       int[] colors1 = { Color.parseColor(this.bodyTxt) };
/*      */       
/* 2347 */       ColorStateList myList = new ColorStateList(states, colors);
/*      */       
/* 2349 */       ColorStateList myList1 = new ColorStateList(states1, colors1);
/* 2350 */       field.set(textInputLayout, myList);
/*      */       
/* 2352 */       Field fDefaultTextColor = TextInputLayout.class.getDeclaredField("mDefaultTextColor");
/* 2353 */       fDefaultTextColor.setAccessible(true);
/* 2354 */       fDefaultTextColor.set(textInputLayout, myList1);
/*      */       
/* 2356 */       Method method = textInputLayout.getClass().getDeclaredMethod("updateLabelState", new Class[] { boolean.class });
/* 2357 */       method.setAccessible(true);
/* 2358 */       method.invoke(textInputLayout, new Object[] { Boolean.valueOf(true) });
/*      */     }
/* 2360 */     catch (Exception e) {
/* 2361 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setCursorColor(EditText view, @ColorInt int color) {
/*      */     try {
/* 2369 */       Field field = TextView.class.getDeclaredField("mCursorDrawableRes");
/* 2370 */       field.setAccessible(true);
/* 2371 */       int drawableResId = field.getInt(view);
/*      */ 
/*      */       
/* 2374 */       field = TextView.class.getDeclaredField("mEditor");
/* 2375 */       field.setAccessible(true);
/* 2376 */       Object editor = field.get(view);
/*      */ 
/*      */       
/* 2379 */       Drawable drawable = ContextCompat.getDrawable(view.getContext(), drawableResId);
/* 2380 */       drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
/* 2381 */       Drawable[] drawables = { drawable, drawable };
/*      */ 
/*      */       
/* 2384 */       field = editor.getClass().getDeclaredField("mCursorDrawable");
/* 2385 */       field.setAccessible(true);
/* 2386 */       field.set(editor, drawables);
/* 2387 */     } catch (Exception exception) {}
/*      */   }
/*      */   
/* 2390 */   String promo_success = "";
/*      */   LinearLayout ll_mkpay;
/*      */   LinearLayout ll_mkpay1;
/*      */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/PaymentOptions.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */