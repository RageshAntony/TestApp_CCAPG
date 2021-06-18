/*     */ package mumbai.dev.sdkdubai;
/*     */ 
/*     */ import android.os.Parcel;
/*     */ import android.os.Parcelable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MerchantDetails
/*     */   implements Parcelable
/*     */ {
/*     */   public MerchantDetails() {}
/*     */   
/*     */   public MerchantDetails(Parcel in) {
/*  15 */     this.access_code = in.readString();
/*  16 */     this.merchant_id = in.readString();
/*  17 */     this.currency = in.readString();
/*  18 */     this.order_id = in.readString();
/*  19 */     this.amount = in.readString();
/*  20 */     this.redirect_url = in.readString();
/*  21 */     this.cancel_url = in.readString();
/*  22 */     this.rsa_url = in.readString();
/*  23 */     this.customer_id = in.readString();
/*  24 */     this.add1 = in.readString();
/*  25 */     this.add2 = in.readString();
/*  26 */     this.add3 = in.readString();
/*  27 */     this.add4 = in.readString();
/*  28 */     this.add5 = in.readString();
/*  29 */     this.promo_code = in.readString();
/*  30 */     this.show_addr = (in.readByte() != 0);
/*  31 */     this.CCAvenue_promo = (in.readByte() != 0);
/*     */   }
/*     */   
/*  34 */   public static final Parcelable.Creator<MerchantDetails> CREATOR = new Parcelable.Creator<MerchantDetails>()
/*     */     {
/*     */       public MerchantDetails createFromParcel(Parcel in) {
/*  37 */         return new MerchantDetails(in);
/*     */       }
/*     */ 
/*     */       
/*     */       public MerchantDetails[] newArray(int size) {
/*  42 */         return new MerchantDetails[size];
/*     */       }
/*     */     };
/*     */   String access_code; String merchant_id; String currency; String order_id; String amount; String redirect_url; String cancel_url; String rsa_url;
/*     */   public String getAccess_code() {
/*  47 */     return this.access_code;
/*     */   }
/*     */   String customer_id; String add1; String add2; String add3; String add4; String add5; String promo_code; boolean show_addr; boolean CCAvenue_promo;
/*     */   public void setAccess_code(String access_code) {
/*  51 */     this.access_code = access_code;
/*     */   }
/*     */   
/*     */   public String getMerchant_id() {
/*  55 */     return this.merchant_id;
/*     */   }
/*     */   
/*     */   public void setMerchant_id(String merchant_id) {
/*  59 */     this.merchant_id = merchant_id;
/*     */   }
/*     */   
/*     */   public String getCurrency() {
/*  63 */     return this.currency;
/*     */   }
/*     */   
/*     */   public void setCurrency(String currency) {
/*  67 */     this.currency = currency;
/*     */   }
/*     */   
/*     */   public String getOrder_id() {
/*  71 */     return this.order_id;
/*     */   }
/*     */   
/*     */   public void setOrder_id(String order_id) {
/*  75 */     this.order_id = order_id;
/*     */   }
/*     */   
/*     */   public String getAmount() {
/*  79 */     return this.amount;
/*     */   }
/*     */   
/*     */   public void setAmount(String amount) {
/*  83 */     this.amount = amount;
/*     */   }
/*     */   
/*     */   public String getRedirect_url() {
/*  87 */     return this.redirect_url;
/*     */   }
/*     */   
/*     */   public void setRedirect_url(String redirect_url) {
/*  91 */     this.redirect_url = redirect_url;
/*     */   }
/*     */   
/*     */   public String getCancel_url() {
/*  95 */     return this.cancel_url;
/*     */   }
/*     */   
/*     */   public void setCancel_url(String cancel_url) {
/*  99 */     this.cancel_url = cancel_url;
/*     */   }
/*     */   
/*     */   public String getRsa_url() {
/* 103 */     return this.rsa_url;
/*     */   }
/*     */   
/*     */   public void setRsa_url(String rsa_url) {
/* 107 */     this.rsa_url = rsa_url;
/*     */   }
/*     */   
/*     */   public String getCustomer_id() {
/* 111 */     return this.customer_id;
/*     */   }
/*     */   
/*     */   public void setCustomer_id(String customer_id) {
/* 115 */     this.customer_id = customer_id;
/*     */   }
/*     */   
/*     */   public String getAdd1() {
/* 119 */     return this.add1;
/*     */   }
/*     */   
/*     */   public void setAdd1(String add1) {
/* 123 */     this.add1 = add1;
/*     */   }
/*     */   
/*     */   public String getAdd2() {
/* 127 */     return this.add2;
/*     */   }
/*     */   
/*     */   public void setAdd2(String add2) {
/* 131 */     this.add2 = add2;
/*     */   }
/*     */   
/*     */   public String getAdd3() {
/* 135 */     return this.add3;
/*     */   }
/*     */   
/*     */   public void setAdd3(String add3) {
/* 139 */     this.add3 = add3;
/*     */   }
/*     */   
/*     */   public String getAdd4() {
/* 143 */     return this.add4;
/*     */   }
/*     */   
/*     */   public void setAdd4(String add4) {
/* 147 */     this.add4 = add4;
/*     */   }
/*     */   
/*     */   public String getAdd5() {
/* 151 */     return this.add5;
/*     */   }
/*     */   
/*     */   public void setAdd5(String add5) {
/* 155 */     this.add5 = add5;
/*     */   }
/*     */   
/*     */   public String getPromo_code() {
/* 159 */     return this.promo_code;
/*     */   }
/*     */   
/*     */   public void setPromo_code(String promo_code) {
/* 163 */     this.promo_code = promo_code;
/*     */   }
/*     */   
/*     */   public boolean isShow_addr() {
/* 167 */     return this.show_addr;
/*     */   }
/*     */   
/*     */   public void setShow_addr(boolean show_addr) {
/* 171 */     this.show_addr = show_addr;
/*     */   }
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
/*     */   public boolean isCCAvenue_promo() {
/* 190 */     return this.CCAvenue_promo;
/*     */   }
/*     */   
/*     */   public void setCCAvenue_promo(boolean CCAvenue_promo) {
/* 194 */     this.CCAvenue_promo = CCAvenue_promo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int describeContents() {
/* 202 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToParcel(Parcel parcel, int i) {
/* 207 */     parcel.writeString(this.access_code);
/* 208 */     parcel.writeString(this.merchant_id);
/* 209 */     parcel.writeString(this.currency);
/* 210 */     parcel.writeString(this.order_id);
/* 211 */     parcel.writeString(this.amount);
/* 212 */     parcel.writeString(this.redirect_url);
/* 213 */     parcel.writeString(this.cancel_url);
/* 214 */     parcel.writeString(this.rsa_url);
/* 215 */     parcel.writeString(this.customer_id);
/* 216 */     parcel.writeString(this.add1);
/* 217 */     parcel.writeString(this.add2);
/* 218 */     parcel.writeString(this.add3);
/* 219 */     parcel.writeString(this.add4);
/* 220 */     parcel.writeString(this.add5);
/* 221 */     parcel.writeString(this.promo_code);
/* 222 */     parcel.writeByte((byte)(this.show_addr ? 1 : 0));
/* 223 */     parcel.writeByte((byte)(this.CCAvenue_promo ? 1 : 0));
/*     */   }
/*     */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/MerchantDetails.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */