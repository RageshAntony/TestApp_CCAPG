/*     */ package mumbai.dev.sdkdubai;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.os.Bundle;
/*     */ import android.view.View;
/*     */ import android.widget.Button;
/*     */ import android.widget.CheckBox;
/*     */ import android.widget.EditText;
/*     */ import android.widget.LinearLayout;
/*     */ import android.widget.TextView;
/*     */ import android.widget.Toast;
/*     */ import androidx.appcompat.app.AppCompatActivity;
/*     */ import androidx.viewpager.widget.PagerAdapter;
/*     */ import androidx.viewpager.widget.ViewPager;
/*     */ import belka.us.androidtoggleswitch.widgets.BaseToggleSwitch;
/*     */ import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
/*     */ import java.util.ArrayList;
/*     */ import mumbai.dev.sdkdubai.dto.CardTypeDTO;
/*     */ import net.cachapa.expandablelayout.ExpandableLayout;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NewCreditCard
/*     */   extends AppCompatActivity
/*     */   implements View.OnClickListener
/*     */ {
/*  32 */   String cardname = "Unknown";
/*     */   
/*     */   int selected_card;
/*     */   ViewPager viewPager;
/*     */   PagerAdapter adapter;
/*     */   private ExpandableLayout expandableLayout0;
/*     */   TextView expand;
/*     */   TextView up;
/*     */   Context mContext;
/*     */   JSONArray vJsonArr;
/*     */   JSONArray cardtypejson;
/*  43 */   String[] cc_list_jsp = new String[] { "Visa", "MasterCard", "Amex", "Discover", "Diners Club", "JCB" };
/*  44 */   ArrayList<CardTypeDTO> card_list = new ArrayList<>();
/*     */   
/*     */   ToggleSwitch toggleSwitch;
/*     */   Button pay_card;
/*     */   EditText ip_exp;
/*     */   EditText ip_cvv;
/*     */   CheckBox fee_checkbox;
/*     */   CreditCardEditText creditcard;
/*     */   TextView tv_inr;
/*     */   TextView tv_orderid;
/*  54 */   int sw = 0;
/*     */   
/*     */   LinearLayout cc;
/*     */   LinearLayout amex;
/*     */   
/*     */   protected void onCreate(Bundle savedInstanceState) {
/*  60 */     super.onCreate(savedInstanceState);
/*  61 */     setContentView(R.layout.activity_new_credit_card);
/*     */     
/*  63 */     this.mContext = (Context)this;
/*     */     
/*  65 */     this.cc = (LinearLayout)findViewById(R.id.cc);
/*  66 */     this.amex = (LinearLayout)findViewById(R.id.amex);
/*  67 */     this.ll_amex_ez = (LinearLayout)findViewById(R.id.ll_amex_ez);
/*  68 */     this.pay_card = (Button)findViewById(R.id.pay_card);
/*  69 */     this.ip_cvv = (EditText)findViewById(R.id.ip_cvv);
/*  70 */     this.ip_exp = (EditText)findViewById(R.id.ip_exp);
/*     */     
/*  72 */     this.toggleSwitch = (ToggleSwitch)findViewById(R.id.toggleswitch);
/*  73 */     this.fee_checkbox = (CheckBox)findViewById(R.id.checkBox1);
/*  74 */     this.tv_inr = (TextView)findViewById(R.id.tv_inr);
/*  75 */     this.tv_orderid = (TextView)findViewById(R.id.tv_orderid);
/*  76 */     this.tv_inr.setText(PaymentOptions.merchant.currency + " " + PaymentOptions.merchant.amount);
/*  77 */     this.tv_orderid.setText("Order #: " + PaymentOptions.merchant.order_id);
/*  78 */     this.pay_card.setText("PAY " + PaymentOptions.merchant.currency + " " + PaymentOptions.merchant.amount);
/*  79 */     if (Constants.VAULT.equalsIgnoreCase("Y")) {
/*  80 */       this.fee_checkbox.setVisibility(0);
/*     */     } else {
/*  82 */       this.fee_checkbox.setVisibility(8);
/*     */     } 
/*  84 */     this.ip_exp.addTextChangedListener(new TwoDigitsCardTextWatcher(this.ip_exp));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     this.expandableLayout0 = (ExpandableLayout)findViewById(R.id.expandable_layout_01);
/*     */     
/* 116 */     this.expand = (TextView)findViewById(R.id.expand);
/* 117 */     this.up = (TextView)findViewById(R.id.up);
/*     */ 
/*     */ 
/*     */     
/* 121 */     this.creditcard = (CreditCardEditText)findViewById(R.id.creditcard);
/*     */     
/* 123 */     this.pay_card.setOnClickListener(this);
/* 124 */     this.expand.setOnClickListener(this);
/* 125 */     this.up.setOnClickListener(this);
/*     */     
/* 127 */     this.cc.setVisibility(0);
/* 128 */     this.amex.setVisibility(8);
/*     */     try {
/* 130 */       this.vJsonArr = new JSONArray(getIntent().getStringExtra("Payment_Type"));
/*     */ 
/*     */       
/* 133 */       for (int i = 0; i < this.vJsonArr.length(); i++) {
/* 134 */         JSONObject jSONObject = this.vJsonArr.getJSONObject(i);
/* 135 */         if (jSONObject.getString("payOpt").equalsIgnoreCase("OPTCRDC"))
/*     */         {
/* 137 */           this.cardtypejson = new JSONArray(jSONObject.getString("cardsList"));
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 142 */       this.card_list.clear();
/*     */       
/* 144 */       for (int j = 0; j < this.cardtypejson.length(); j++) {
/* 145 */         JSONObject card = this.cardtypejson.getJSONObject(j);
/* 146 */         CardTypeDTO cardTypeDTO = new CardTypeDTO();
/* 147 */         cardTypeDTO.setCardName(card.getString("cardName"));
/*     */         
/* 149 */         if (card.getString("cardName").equalsIgnoreCase("Amex ezeClick"))
/*     */         {
/* 151 */           this.amex_ez = true;
/*     */         }
/* 153 */         cardTypeDTO.setCardType(card.getString("cardType"));
/* 154 */         cardTypeDTO.setPayOptType(card.getString("payOptType"));
/* 155 */         cardTypeDTO.setDataAcceptedAt(card.getString("dataAcceptedAt"));
/* 156 */         cardTypeDTO.setStatus(card.getString("status"));
/* 157 */         this.card_list.add(cardTypeDTO);
/*     */       }
/*     */     
/* 160 */     } catch (JSONException e) {
/* 161 */       e.printStackTrace();
/*     */     } 
/*     */ 
/*     */     
/* 165 */     if (this.amex_ez) {
/*     */       
/* 167 */       this.ll_amex_ez.setVisibility(0);
/*     */     }
/*     */     else {
/*     */       
/* 171 */       this.ll_amex_ez.setVisibility(8);
/*     */     } 
/*     */     
/* 174 */     this.toggleSwitch.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener()
/*     */         {
/*     */ 
/*     */ 
/*     */           
/*     */           public void onToggleSwitchChangeListener(int position, boolean isChecked)
/*     */           {
/* 181 */             if (position == 0) {
/* 182 */               NewCreditCard.this.sw = 0;
/* 183 */               NewCreditCard.this.cc.setVisibility(0);
/* 184 */               NewCreditCard.this.amex.setVisibility(8);
/* 185 */             } else if (position == 1) {
/* 186 */               NewCreditCard.this.sw = 1;
/* 187 */               NewCreditCard.this.cc.setVisibility(8);
/* 188 */               NewCreditCard.this.amex.setVisibility(0);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   LinearLayout ll_amex_ez;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean amex_ez = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String TAG = "NewCreditCard";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick(View v) {
/* 220 */     int id = v.getId();
/*     */     
/* 222 */     if (id == R.id.expand) {
/* 223 */       if (this.expandableLayout0.isExpanded()) {
/* 224 */         this.expand.setVisibility(0);
/* 225 */         this.expandableLayout0.collapse();
/*     */       } else {
/* 227 */         this.expand.setVisibility(8);
/* 228 */         this.expandableLayout0.expand();
/*     */       }
/*     */     
/* 231 */     } else if (id == R.id.up) {
/* 232 */       if (this.expandableLayout0.isExpanded()) {
/*     */         
/* 234 */         this.expand.setVisibility(0);
/* 235 */         this.expandableLayout0.collapse();
/*     */       } else {
/* 237 */         this.expand.setVisibility(0);
/* 238 */         this.expandableLayout0.expand();
/*     */       }
/*     */     
/* 241 */     } else if (id == R.id.pay_card) {
/* 242 */       if (this.sw == 0) {
/* 243 */         if (!Check(this.creditcard.getText().toString())) {
/* 244 */           showToast("Please enter valid credit card number");
/*     */         
/*     */         }
/* 247 */         else if (this.ip_exp.getText().toString().length() != 5) {
/* 248 */           showToast("Please enter valid expiry date");
/* 249 */         } else if (this.ip_cvv.getText().toString().length() != 3) {
/* 250 */           showToast("Please enter valid CVV");
/*     */         } else {
/*     */           
/* 253 */           pay();
/*     */         }
/*     */       
/* 256 */       } else if (this.sw == 1) {
/* 257 */         pay1();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onStart() {
/* 266 */     super.onStart();
/*     */   }
/*     */ 
/*     */   
/*     */   public void showToast(String msg) {
/* 271 */     Toast.makeText((Context)this, msg, 1).show();
/*     */   }
/*     */ 
/*     */   
/*     */   public void pay() {
/* 276 */     String[] cc_list_jsp = { "Visa", "MasterCard", "Amex", "Discover", "Diners Club", "JCB" };
/*     */     
/* 278 */     if (CreditCardEditText.selected_card != 0)
/*     */     {
/* 280 */       if (CreditCardEditText.selected_card == R.drawable.visa) {
/*     */         
/* 282 */         this.cardname = "Visa";
/*     */       }
/* 284 */       else if (CreditCardEditText.selected_card == R.drawable.master) {
/*     */         
/* 286 */         this.cardname = "MasterCard";
/*     */       }
/* 288 */       else if (CreditCardEditText.selected_card == R.drawable.amex) {
/*     */         
/* 290 */         this.cardname = "Amex";
/*     */       }
/* 292 */       else if (CreditCardEditText.selected_card == R.drawable.discover) {
/*     */         
/* 294 */         this.cardname = "Discover";
/*     */       }
/* 296 */       else if (CreditCardEditText.selected_card == R.drawable.dinersclub) {
/*     */         
/* 298 */         this.cardname = "Diners Club";
/*     */       }
/* 300 */       else if (CreditCardEditText.selected_card == R.drawable.jcb) {
/*     */         
/* 302 */         this.cardname = "JCB";
/*     */       } else {
/*     */         
/* 305 */         this.cardname = "Unknown";
/*     */       } 
/*     */     }
/* 308 */     if (!this.cardname.equalsIgnoreCase("Unknown")) {
/*     */ 
/*     */       
/* 311 */       boolean flg = false;
/* 312 */       for (int j = 0; j < this.card_list.size(); j++) {
/* 313 */         if (this.cardname.equalsIgnoreCase(((CardTypeDTO)this.card_list.get(j)).getCardName())) {
/* 314 */           this.selected_card = j;
/* 315 */           flg = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 320 */       if (flg == true) {
/*     */         
/* 322 */         String vOrderId = ServiceUtility.chkNull(PaymentOptions.merchant.getOrder_id()).toString().trim();
/* 323 */         String vRsaKeyUrl = ServiceUtility.chkNull(PaymentOptions.merchant.getRsa_url()).toString().trim();
/* 324 */         if (!vOrderId.equals("") && !vRsaKeyUrl.equals("")) {
/* 325 */           Intent intent = new Intent((Context)this, WebViewActivity.class);
/* 326 */           intent.putExtra("order_id", ServiceUtility.chkNull(PaymentOptions.merchant.getOrder_id()).toString().trim());
/* 327 */           intent.putExtra("access_code", ServiceUtility.chkNull(PaymentOptions.merchant.getAccess_code()).toString().trim());
/* 328 */           intent.putExtra("merchant_id", ServiceUtility.chkNull(PaymentOptions.merchant.getMerchant_id()).toString().trim());
/* 329 */           intent.putExtra("billing_name", ServiceUtility.chkNull(PaymentOptions.billing.getName()).toString().trim());
/* 330 */           intent.putExtra("billing_address", ServiceUtility.chkNull(PaymentOptions.billing.getAddress()).toString().trim());
/* 331 */           intent.putExtra("billing_country", ServiceUtility.chkNull(PaymentOptions.billing.getCountry()).toString().trim());
/* 332 */           intent.putExtra("billing_state", ServiceUtility.chkNull(PaymentOptions.billing.getState()).toString().trim());
/* 333 */           intent.putExtra("billing_city", ServiceUtility.chkNull(PaymentOptions.billing.getCity()).toString().trim());
/*     */           
/* 335 */           intent.putExtra("billing_tel", ServiceUtility.chkNull(PaymentOptions.billing.getTelephone()).toString().trim());
/* 336 */           intent.putExtra("billing_email", ServiceUtility.chkNull(PaymentOptions.billing.getEmail()).toString().trim());
/* 337 */           intent.putExtra("delivery_name", ServiceUtility.chkNull(PaymentOptions.shipping.getName()).toString().trim());
/* 338 */           intent.putExtra("delivery_address", ServiceUtility.chkNull(PaymentOptions.shipping.getAddress()).toString().trim());
/* 339 */           intent.putExtra("delivery_country", ServiceUtility.chkNull(PaymentOptions.shipping.getCountry()).toString().trim());
/* 340 */           intent.putExtra("delivery_state", ServiceUtility.chkNull(PaymentOptions.shipping.getState()).toString().trim());
/* 341 */           intent.putExtra("delivery_city", ServiceUtility.chkNull(PaymentOptions.shipping.getCity()).toString().trim());
/*     */           
/* 343 */           intent.putExtra("delivery_tel", ServiceUtility.chkNull(PaymentOptions.shipping.getTelephone()).toString().trim());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 349 */           intent.putExtra("cvv_number", this.ip_cvv.getText().toString());
/* 350 */           intent.putExtra("redirect_url", ServiceUtility.chkNull(PaymentOptions.merchant.getRedirect_url()).toString().trim());
/* 351 */           intent.putExtra("cancel_url", ServiceUtility.chkNull(PaymentOptions.merchant.getCancel_url()).toString().trim());
/* 352 */           intent.putExtra("rsa_key_url", ServiceUtility.chkNull(PaymentOptions.merchant.getRsa_url()).toString().trim());
/* 353 */           intent.putExtra("payment_option", ((CardTypeDTO)this.card_list.get(this.selected_card)).getPayOptType());
/* 354 */           intent.putExtra("card_number", ServiceUtility.chkNull(this.creditcard.getText()).toString().replaceAll(" ", "").trim());
/* 355 */           intent.putExtra("expiry_month", ServiceUtility.chkNull(this.ip_exp.getText().toString().substring(0, 2)).toString().trim());
/* 356 */           intent.putExtra("expiry_year", ServiceUtility.chkNull("20" + this.ip_exp.getText().toString().substring(3, 5)).toString().trim());
/* 357 */           intent.putExtra("issuing_bank", "");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 390 */           intent.putExtra("card_type", ((CardTypeDTO)this.card_list.get(this.selected_card)).getCardType());
/* 391 */           intent.putExtra("card_name", ((CardTypeDTO)this.card_list.get(this.selected_card)).getCardName());
/* 392 */           if (((CardTypeDTO)this.card_list.get(this.selected_card)).getDataAcceptedAt().equalsIgnoreCase("CCAvenue")) {
/* 393 */             intent.putExtra("data_accept", "Y");
/*     */           } else {
/* 395 */             intent.putExtra("data_accept", "N");
/* 396 */           }  intent.putExtra("customer_identifier", PaymentOptions.merchant.getCustomer_id());
/* 397 */           intent.putExtra("currency", ServiceUtility.chkNull(PaymentOptions.merchant.getCurrency()).toString().trim());
/* 398 */           intent.putExtra("amount", ServiceUtility.chkNull(PaymentOptions.merchant.getAmount()).toString().trim());
/*     */           
/* 400 */           if (this.fee_checkbox.isChecked())
/* 401 */             intent.putExtra("saveCard", "Y"); 
/* 402 */           startActivity(intent);
/*     */         } else {
/* 404 */           showToast("Amount/Currency/Access code/Merchant Id & RSA key Url are mandatory.");
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 409 */         showToast(this.cardname + " payment option is not supported.");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pay1() {
/* 418 */     String vOrderId = ServiceUtility.chkNull(PaymentOptions.merchant.getOrder_id()).toString().trim();
/* 419 */     String vRsaKeyUrl = ServiceUtility.chkNull(PaymentOptions.merchant.getRsa_url()).toString().trim();
/* 420 */     if (!vOrderId.equals("") && !vRsaKeyUrl.equals("")) {
/* 421 */       Intent intent = new Intent((Context)this, WebViewActivity.class);
/* 422 */       intent.putExtra("order_id", ServiceUtility.chkNull(PaymentOptions.merchant.getOrder_id()).toString().trim());
/* 423 */       intent.putExtra("access_code", ServiceUtility.chkNull(PaymentOptions.merchant.getAccess_code()).toString().trim());
/* 424 */       intent.putExtra("merchant_id", ServiceUtility.chkNull(PaymentOptions.merchant.getMerchant_id()).toString().trim());
/* 425 */       intent.putExtra("billing_name", ServiceUtility.chkNull(PaymentOptions.billing.getName()).toString().trim());
/* 426 */       intent.putExtra("billing_address", ServiceUtility.chkNull(PaymentOptions.billing.getAddress()).toString().trim());
/* 427 */       intent.putExtra("billing_country", ServiceUtility.chkNull(PaymentOptions.billing.getCountry()).toString().trim());
/* 428 */       intent.putExtra("billing_state", ServiceUtility.chkNull(PaymentOptions.billing.getState()).toString().trim());
/* 429 */       intent.putExtra("billing_city", ServiceUtility.chkNull(PaymentOptions.billing.getCity()).toString().trim());
/*     */       
/* 431 */       intent.putExtra("billing_tel", ServiceUtility.chkNull(PaymentOptions.billing.getTelephone()).toString().trim());
/* 432 */       intent.putExtra("billing_email", ServiceUtility.chkNull(PaymentOptions.billing.getEmail()).toString().trim());
/* 433 */       intent.putExtra("delivery_name", ServiceUtility.chkNull(PaymentOptions.shipping.getName()).toString().trim());
/* 434 */       intent.putExtra("delivery_address", ServiceUtility.chkNull(PaymentOptions.shipping.getAddress()).toString().trim());
/* 435 */       intent.putExtra("delivery_country", ServiceUtility.chkNull(PaymentOptions.shipping.getCountry()).toString().trim());
/* 436 */       intent.putExtra("delivery_state", ServiceUtility.chkNull(PaymentOptions.shipping.getState()).toString().trim());
/* 437 */       intent.putExtra("delivery_city", ServiceUtility.chkNull(PaymentOptions.shipping.getCity()).toString().trim());
/*     */       
/* 439 */       intent.putExtra("delivery_tel", ServiceUtility.chkNull(PaymentOptions.shipping.getTelephone()).toString().trim());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 445 */       intent.putExtra("cvv_number", "");
/* 446 */       intent.putExtra("redirect_url", ServiceUtility.chkNull(PaymentOptions.merchant.getRedirect_url()).toString().trim());
/* 447 */       intent.putExtra("cancel_url", ServiceUtility.chkNull(PaymentOptions.merchant.getCancel_url()).toString().trim());
/* 448 */       intent.putExtra("rsa_key_url", ServiceUtility.chkNull(PaymentOptions.merchant.getRsa_url()).toString().trim());
/* 449 */       intent.putExtra("payment_option", "OPTCRDC");
/* 450 */       intent.putExtra("card_number", "");
/* 451 */       intent.putExtra("expiry_month", "");
/* 452 */       intent.putExtra("expiry_year", "");
/* 453 */       intent.putExtra("issuing_bank", "");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 486 */       intent.putExtra("card_type", "CRDC");
/* 487 */       intent.putExtra("card_name", "Amex ezeClick");
/*     */       
/* 489 */       intent.putExtra("data_accept", "Y");
/*     */ 
/*     */       
/* 492 */       intent.putExtra("customer_identifier", PaymentOptions.merchant.getCustomer_id());
/* 493 */       intent.putExtra("currency", ServiceUtility.chkNull(PaymentOptions.merchant.getCurrency()).toString().trim());
/* 494 */       intent.putExtra("amount", ServiceUtility.chkNull(PaymentOptions.merchant.getAmount()).toString().trim());
/*     */ 
/*     */       
/* 497 */       startActivity(intent);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean Check(String ccNumber) {
/* 504 */     int sum = 0;
/* 505 */     boolean alternate = false;
/* 506 */     for (int i = ccNumber.length() - 1; i >= 0; i--) {
/*     */       
/* 508 */       int n = Integer.parseInt(ccNumber.substring(i, i + 1));
/* 509 */       if (alternate) {
/*     */         
/* 511 */         n *= 2;
/* 512 */         if (n > 9)
/*     */         {
/* 514 */           n = n % 10 + 1;
/*     */         }
/*     */       } 
/* 517 */       sum += n;
/* 518 */       alternate = !alternate;
/*     */     } 
/* 520 */     return (sum % 10 == 0);
/*     */   }
/*     */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/NewCreditCard.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */