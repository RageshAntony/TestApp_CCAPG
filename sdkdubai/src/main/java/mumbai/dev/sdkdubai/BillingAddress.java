/*     */ package mumbai.dev.sdkdubai;
/*     */ 
/*     */ import android.os.Parcel;
/*     */ import android.os.Parcelable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BillingAddress
/*     */   implements Parcelable
/*     */ {
/*     */   String name;
/*     */   String address;
/*     */   String city;
/*     */   String state;
/*     */   String country;
/*     */   String telephone;
/*     */   String email;
/*     */   
/*     */   public BillingAddress() {}
/*     */   
/*     */   public void writeToParcel(Parcel dest, int flags) {
/*  25 */     dest.writeString(this.name);
/*  26 */     dest.writeString(this.address);
/*  27 */     dest.writeString(this.city);
/*  28 */     dest.writeString(this.state);
/*  29 */     dest.writeString(this.country);
/*     */     
/*  31 */     dest.writeString(this.telephone);
/*  32 */     dest.writeString(this.email);
/*     */   }
/*     */ 
/*     */   
/*     */   public int describeContents() {
/*  37 */     return 0;
/*     */   }
/*     */   
/*  40 */   public static final Parcelable.Creator<BillingAddress> CREATOR = new Parcelable.Creator<BillingAddress>()
/*     */     {
/*     */       public BillingAddress createFromParcel(Parcel in) {
/*  43 */         return new BillingAddress(in);
/*     */       }
/*     */ 
/*     */       
/*     */       public BillingAddress[] newArray(int size) {
/*  48 */         return new BillingAddress[size];
/*     */       }
/*     */     };
/*     */   
/*     */   public String getName() {
/*  53 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  57 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getAddress() {
/*  61 */     return this.address;
/*     */   }
/*     */   
/*     */   public void setAddress(String address) {
/*  65 */     this.address = address;
/*     */   }
/*     */   
/*     */   public String getCity() {
/*  69 */     return this.city;
/*     */   }
/*     */   
/*     */   public void setCity(String city) {
/*  73 */     this.city = city;
/*     */   }
/*     */   
/*     */   public String getState() {
/*  77 */     return this.state;
/*     */   }
/*     */   
/*     */   public void setState(String state) {
/*  81 */     this.state = state;
/*     */   }
/*     */   
/*     */   public String getCountry() {
/*  85 */     return this.country;
/*     */   }
/*     */   
/*     */   public void setCountry(String country) {
/*  89 */     this.country = country;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTelephone() {
/*  94 */     return this.telephone;
/*     */   }
/*     */   
/*     */   public void setTelephone(String telephone) {
/*  98 */     this.telephone = telephone;
/*     */   }
/*     */   
/*     */   public String getEmail() {
/* 102 */     return this.email;
/*     */   }
/*     */   
/*     */   public void setEmail(String email) {
/* 106 */     this.email = email;
/*     */   }
/*     */ 
/*     */   
/*     */   public BillingAddress(Parcel in) {
/* 111 */     this.name = in.readString();
/* 112 */     this.address = in.readString();
/* 113 */     this.city = in.readString();
/* 114 */     this.state = in.readString();
/* 115 */     this.country = in.readString();
/*     */     
/* 117 */     this.telephone = in.readString();
/* 118 */     this.email = in.readString();
/*     */   }
/*     */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/BillingAddress.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */