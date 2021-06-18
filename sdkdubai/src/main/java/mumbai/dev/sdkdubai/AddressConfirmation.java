/*     */ package mumbai.dev.sdkdubai;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.os.Bundle;
/*     */ import android.view.View;
/*     */ import android.view.inputmethod.InputMethodManager;
/*     */ import android.widget.Button;
/*     */ import android.widget.EditText;
/*     */ import android.widget.ProgressBar;
/*     */ import android.widget.RelativeLayout;
/*     */ import android.widget.Toast;
/*     */ import androidx.appcompat.app.AppCompatActivity;
/*     */ import net.cachapa.expandablelayout.ExpandableLayout;
/*     */ import org.json.JSONObject;
/*     */ import retrofit2.Call;
/*     */ import retrofit2.Callback;
/*     */ import retrofit2.Response;
/*     */ 
/*     */ 
/*     */ public class AddressConfirmation
/*     */   extends AppCompatActivity
/*     */   implements View.OnClickListener
/*     */ {
/*     */   ExpandableLayout exp_billing_address;
/*     */   ExpandableLayout exp_shipping_address;
/*     */   Button btn_billing_address;
/*     */   Button btn_shipping_address;
/*     */   Button sdk;
/*     */   EditText ed_address_b;
/*     */   EditText ed_name_b;
/*     */   EditText ed_country_b;
/*     */   EditText ed_state_b;
/*     */   EditText ed_city_b;
/*     */   EditText ed_zip_b;
/*     */   EditText ed_telephone_b;
/*     */   EditText ed_email_b;
/*  38 */   int i = 0; EditText ed_address_s; EditText ed_name_s; EditText ed_country_s; EditText ed_state_s; EditText ed_city_s; EditText ed_zip_s; EditText ed_telephone_s; EditText ed_email_s; ProgressBar progressBar1; Button pay; MerchantDetails merchant; BillingAddress billing; ShippingAddress shipping;
/*     */   RelativeLayout address;
/*     */   
/*     */   protected void onCreate(Bundle savedInstanceState) {
/*  42 */     super.onCreate(savedInstanceState);
/*  43 */     setContentView(R.layout.activity_address_confirmation);
/*     */     
/*  45 */     Intent intent = getIntent();
/*  46 */     this.merchant = (MerchantDetails)intent.getParcelableExtra("merchant");
/*  47 */     this.billing = (BillingAddress)intent.getParcelableExtra("billing");
/*  48 */     this.shipping = (ShippingAddress)intent.getParcelableExtra("shipping");
/*  49 */     this.pay = (Button)findViewById(R.id.pay);
/*  50 */     this.progressBar1 = (ProgressBar)findViewById(R.id.progressBar1);
/*  51 */     this.address = (RelativeLayout)findViewById(R.id.address);
/*  52 */     this.ed_address_s = (EditText)findViewById(R.id.ed_address_s);
/*  53 */     this.ed_name_s = (EditText)findViewById(R.id.ed_name_s);
/*  54 */     this.ed_country_s = (EditText)findViewById(R.id.ed_country_s);
/*  55 */     this.ed_state_s = (EditText)findViewById(R.id.ed_state_s);
/*  56 */     this.ed_city_s = (EditText)findViewById(R.id.ed_city_s);
/*  57 */     this.ed_zip_s = (EditText)findViewById(R.id.ed_zip_s);
/*  58 */     this.ed_telephone_s = (EditText)findViewById(R.id.ed_telephone_s);
/*  59 */     this.ed_email_s = (EditText)findViewById(R.id.ed_email_s);
/*     */     
/*  61 */     this.ed_address_s.setText(this.shipping.getAddress());
/*  62 */     this.ed_name_s.setText(this.shipping.getName());
/*  63 */     this.ed_country_s.setText(this.shipping.getCountry());
/*  64 */     this.ed_state_s.setText(this.shipping.getState());
/*  65 */     this.ed_city_s.setText(this.shipping.getCity());
/*     */     
/*  67 */     this.ed_telephone_s.setText(this.shipping.getTelephone());
/*     */ 
/*     */ 
/*     */     
/*  71 */     this.ed_address_b = (EditText)findViewById(R.id.ed_address_b);
/*  72 */     this.ed_name_b = (EditText)findViewById(R.id.ed_name_b);
/*  73 */     this.ed_country_b = (EditText)findViewById(R.id.ed_country_b);
/*  74 */     this.ed_state_b = (EditText)findViewById(R.id.ed_state_b);
/*  75 */     this.ed_city_b = (EditText)findViewById(R.id.ed_city_b);
/*  76 */     this.ed_zip_b = (EditText)findViewById(R.id.ed_zip_b);
/*  77 */     this.ed_telephone_b = (EditText)findViewById(R.id.ed_telephone_b);
/*  78 */     this.ed_email_b = (EditText)findViewById(R.id.ed_email_b);
/*     */ 
/*     */     
/*  81 */     this.ed_address_b.setText(this.billing.getAddress());
/*  82 */     this.ed_name_b.setText(this.billing.getName());
/*  83 */     this.ed_country_b.setText(this.billing.getCountry());
/*  84 */     this.ed_state_b.setText(this.billing.getState());
/*  85 */     this.ed_city_b.setText(this.billing.getCity());
/*     */     
/*  87 */     this.ed_telephone_b.setText(this.billing.getTelephone());
/*  88 */     this.ed_email_b.setText(this.billing.getEmail());
/*     */     
/*  90 */     this.btn_billing_address = (Button)findViewById(R.id.btn_billing_address);
/*  91 */     this.btn_shipping_address = (Button)findViewById(R.id.btn_shipping_address);
/*     */ 
/*     */     
/*  94 */     this.exp_billing_address = (ExpandableLayout)findViewById(R.id.exp_billing_address);
/*  95 */     this.exp_shipping_address = (ExpandableLayout)findViewById(R.id.exp_shipping_address);
/*     */     
/*  97 */     this.exp_billing_address.expand();
/*     */     
/*  99 */     this.btn_billing_address.setOnClickListener(this);
/* 100 */     this.btn_shipping_address.setOnClickListener(this);
/*     */     
/* 102 */     if (this.merchant.getAccess_code() == null || this.merchant.getAccess_code().length() < 1) {
/* 103 */       showToast("Invalid Access Code!!!");
/* 104 */       finish();
/*     */     } else {
/*     */       
/* 107 */       demo1();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     this.pay.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */ 
/*     */ 
/*     */           
/*     */           public void onClick(View v)
/*     */           {
/* 125 */             AddressConfirmation.this.billing.setName(AddressConfirmation.this.ed_name_b.getText().toString().trim());
/* 126 */             AddressConfirmation.this.billing.setAddress(AddressConfirmation.this.ed_address_b.getText().toString().trim());
/* 127 */             AddressConfirmation.this.billing.setCountry(AddressConfirmation.this.ed_country_b.getText().toString().trim());
/* 128 */             AddressConfirmation.this.billing.setState(AddressConfirmation.this.ed_state_b.getText().toString().trim());
/* 129 */             AddressConfirmation.this.billing.setCity(AddressConfirmation.this.ed_city_b.getText().toString().trim());
/*     */             
/* 131 */             AddressConfirmation.this.billing.setTelephone(AddressConfirmation.this.ed_telephone_b.getText().toString().trim());
/* 132 */             AddressConfirmation.this.billing.setEmail(AddressConfirmation.this.ed_email_b.getText().toString().trim());
/*     */ 
/*     */ 
/*     */             
/* 136 */             AddressConfirmation.this.shipping.setName(AddressConfirmation.this.ed_name_s.getText().toString().trim());
/* 137 */             AddressConfirmation.this.shipping.setAddress(AddressConfirmation.this.ed_address_s.getText().toString().trim());
/* 138 */             AddressConfirmation.this.shipping.setCountry(AddressConfirmation.this.ed_country_s.getText().toString().trim());
/* 139 */             AddressConfirmation.this.shipping.setState(AddressConfirmation.this.ed_state_s.getText().toString().trim());
/* 140 */             AddressConfirmation.this.shipping.setCity(AddressConfirmation.this.ed_city_s.getText().toString().trim());
/*     */             
/* 142 */             AddressConfirmation.this.shipping.setTelephone(AddressConfirmation.this.ed_telephone_s.getText().toString().trim());
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
/* 165 */             Intent i = new Intent((Context)AddressConfirmation.this, PaymentOptions.class);
/*     */             
/* 167 */             i.putExtra("merchant", AddressConfirmation.this.merchant);
/* 168 */             i.putExtra("billing", AddressConfirmation.this.billing);
/* 169 */             i.putExtra("shipping", AddressConfirmation.this.shipping);
/* 170 */             AddressConfirmation.this.startActivity(i);
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
/*     */   public void showToast(String msg) {
/* 182 */     Toast.makeText((Context)this, msg, 1).show();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean validate() {
/* 188 */     if (this.merchant.getAccess_code() == null || this.merchant.getAccess_code().length() < 1) {
/* 189 */       showToast("Invalid Access Code!!!");
/* 190 */       return false;
/*     */     } 
/*     */     
/* 193 */     if (this.merchant.getOrder_id() == null || this.merchant.getOrder_id().length() < 1) {
/* 194 */       showToast("Invalid Order id!!!");
/* 195 */       return false;
/*     */     } 
/*     */     
/* 198 */     if (this.merchant.getCurrency() == null || this.merchant.getCurrency().length() < 1) {
/* 199 */       showToast("Invalid Currency!!!");
/* 200 */       return false;
/*     */     } 
/*     */     
/* 203 */     if (this.merchant.getAmount() == null || this.merchant.getAmount().length() < 1) {
/* 204 */       showToast("Invalid Amount!!!");
/* 205 */       return false;
/*     */     } 
/*     */     
/* 208 */     if (this.merchant.getMerchant_id() == null || this.merchant.getMerchant_id().length() < 1) {
/* 209 */       showToast("Invalid Merchant id!!!");
/* 210 */       return false;
/*     */     } 
/*     */     
/* 213 */     if (this.merchant.getRedirect_url() == null || this.merchant.getRedirect_url().length() < 1) {
/* 214 */       showToast("oh.. Invalid redirect url!!! ");
/* 215 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 219 */     if (this.merchant.getCancel_url() == null || this.merchant.getCancel_url().length() < 1) {
/* 220 */       showToast("Invalid cancel url!!!");
/* 221 */       return false;
/*     */     } 
/*     */     
/* 224 */     if (this.merchant.getRsa_url() == null || this.merchant.getRsa_url().length() < 1) {
/* 225 */       showToast("Invalid rsa url!!!");
/* 226 */       return false;
/*     */     } 
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
/*     */ 
/*     */ 
/*     */     
/* 295 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick(View v) {
/* 302 */     int id = v.getId();
/* 303 */     if (id == R.id.btn_billing_address) {
/*     */       
/* 305 */       this.exp_billing_address.toggle();
/*     */       
/* 307 */       if (this.exp_billing_address.isExpanded()) {
/* 308 */         this.btn_billing_address.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.expand_less, 0);
/*     */       } else {
/* 310 */         this.btn_billing_address.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.expand_more, 0);
/*     */       }
/*     */     
/* 313 */     } else if (id == R.id.btn_shipping_address) {
/*     */       
/* 315 */       this.exp_shipping_address.toggle();
/* 316 */       if (this.exp_shipping_address.isExpanded()) {
/* 317 */         this.btn_shipping_address.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.expand_less, 0);
/*     */       } else {
/* 319 */         this.btn_shipping_address.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.expand_more, 0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void getSettings(String ac) {
/* 326 */     LoadingDialog.showLoadingDialog((Context)this, "Loading....");
/* 327 */     RetrofitClient.GitApiInterface service = RetrofitClient.Indianrail();
/* 328 */     String body = "{\n\t\"accessCode\": \"" + ac + "\"\n}";
/*     */ 
/*     */     
/* 331 */     Call<String> call = service.post_setting(body);
/*     */     
/* 333 */     call.enqueue(new Callback<String>()
/*     */         {
/*     */           public void onResponse(Call<String> call, Response<String> response)
/*     */           {
/*     */             try {
/* 338 */               LoadingDialog.cancelLoading();
/*     */               
/* 340 */               if (response.body() != null) {
/*     */                 
/* 342 */                 JSONObject j = new JSONObject(((String)response.body()).toString());
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 347 */                 if (AddressConfirmation.this.merchant.isShow_addr()) {
/* 348 */                   AddressConfirmation.this.progressBar1.setVisibility(8);
/* 349 */                   AddressConfirmation.this.address.setVisibility(0);
/* 350 */                   AddressConfirmation.this.pay.setVisibility(0);
/* 351 */                   boolean chk = AddressConfirmation.this.validate();
/* 352 */                   if (!chk) {
/* 353 */                     AddressConfirmation.this.finish();
/*     */                   }
/*     */                 } else {
/*     */                   
/* 357 */                   boolean chk = AddressConfirmation.this.validate();
/* 358 */                   if (!chk) {
/* 359 */                     AddressConfirmation.this.finish();
/*     */                   } else {
/* 361 */                     AddressConfirmation.this.finish();
/* 362 */                     Intent i = new Intent((Context)AddressConfirmation.this, PaymentOptions.class);
/*     */ 
/*     */                     
/* 365 */                     i.putExtra("merchant", AddressConfirmation.this.merchant);
/* 366 */                     i.putExtra("billing", AddressConfirmation.this.billing);
/* 367 */                     i.putExtra("shipping", AddressConfirmation.this.shipping);
/* 368 */                     AddressConfirmation.this.startActivity(i);
/*     */                   }
/*     */                 
/*     */                 } 
/*     */               } else {
/*     */                 
/* 374 */                 Toast.makeText((Context)AddressConfirmation.this, "No Response. Please try Again!!!", 1).show();
/* 375 */                 AddressConfirmation.this.finish();
/*     */               }
/*     */             
/*     */             }
/* 379 */             catch (Exception e) {
/*     */               
/* 381 */               Toast.makeText((Context)AddressConfirmation.this, "Error. Please try Again!!!", 1).show();
/* 382 */               LoadingDialog.cancelLoading();
/* 383 */               AddressConfirmation.this.finish();
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public void onFailure(Call<String> call, Throwable t) {
/* 390 */             if (t instanceof java.net.SocketTimeoutException) {
/* 391 */               LoadingDialog.cancelLoading();
/* 392 */               Toast.makeText((Context)AddressConfirmation.this, "Something went wrong. Please try Again!!!", 1).show();
/*     */             } else {
/*     */               
/* 395 */               LoadingDialog.cancelLoading();
/* 396 */               Toast.makeText((Context)AddressConfirmation.this, "Something went wrong. Please try Again!!!", 1).show();
/*     */             } 
/*     */             
/* 399 */             AddressConfirmation.this.finish();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void hideSoftKeyboard() {
/* 409 */     if (getCurrentFocus() != null) {
/* 410 */       InputMethodManager inputMethodManager = (InputMethodManager)getSystemService("input_method");
/* 411 */       inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void demo() {
/* 417 */     LoadingDialog.cancelLoading();
/* 418 */     if (this.merchant.isShow_addr()) {
/* 419 */       this.progressBar1.setVisibility(8);
/* 420 */       this.address.setVisibility(0);
/* 421 */       this.pay.setVisibility(0);
/* 422 */       boolean chk = validate();
/* 423 */       if (!chk) {
/* 424 */         finish();
/*     */       }
/*     */     } else {
/*     */       
/* 428 */       boolean chk = validate();
/* 429 */       if (!chk) {
/* 430 */         finish();
/*     */       } else {
/* 432 */         finish();
/* 433 */         Intent i = new Intent((Context)this, PaymentOptions.class);
/*     */ 
/*     */         
/* 436 */         i.putExtra("merchant", this.merchant);
/* 437 */         i.putExtra("billing", this.billing);
/* 438 */         i.putExtra("shipping", this.shipping);
/* 439 */         startActivity(i);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void demo1() {
/* 446 */     LoadingDialog.cancelLoading();
/* 447 */     if (this.merchant.isShow_addr()) {
/* 448 */       this.progressBar1.setVisibility(8);
/* 449 */       this.address.setVisibility(0);
/* 450 */       this.pay.setVisibility(0);
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 455 */     else if (this.merchant.isShow_addr()) {
/* 456 */       this.progressBar1.setVisibility(8);
/* 457 */       this.address.setVisibility(0);
/* 458 */       this.pay.setVisibility(0);
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
/*     */     }
/* 471 */     else if (!this.merchant.isShow_addr()) {
/* 472 */       this.progressBar1.setVisibility(0);
/* 473 */       this.address.setVisibility(8);
/* 474 */       this.pay.setVisibility(8);
/*     */       
/* 476 */       boolean chk = validate();
/* 477 */       if (chk) {
/* 478 */         finish();
/* 479 */         Intent i = new Intent((Context)this, PaymentOptions.class);
/*     */ 
/*     */         
/* 482 */         i.putExtra("merchant", this.merchant);
/* 483 */         i.putExtra("billing", this.billing);
/* 484 */         i.putExtra("shipping", this.shipping);
/* 485 */         startActivity(i);
/*     */       } else {
/*     */         
/* 488 */         finish();
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 494 */     else if (!this.merchant.isShow_addr()) {
/* 495 */       this.progressBar1.setVisibility(0);
/* 496 */       this.address.setVisibility(8);
/* 497 */       this.pay.setVisibility(8);
/*     */ 
/*     */       
/* 500 */       finish();
/* 501 */       Intent i = new Intent((Context)this, PaymentOptions.class);
/*     */ 
/*     */       
/* 504 */       i.putExtra("merchant", this.merchant);
/* 505 */       i.putExtra("billing", this.billing);
/* 506 */       i.putExtra("shipping", this.shipping);
/* 507 */       startActivity(i);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/AddressConfirmation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */