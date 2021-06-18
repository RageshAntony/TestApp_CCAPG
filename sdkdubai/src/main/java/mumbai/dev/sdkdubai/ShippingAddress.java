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
/*     */ 
/*     */ 
/*     */ public class ShippingAddress
/*     */   implements Parcelable
/*     */ {
/*     */   String name;
/*     */   String address;
/*     */   String city;
/*     */   String state;
/*     */   String country;
/*     */   String telephone;
/*     */   
/*     */   public ShippingAddress() {}
/*     */   
/*     */   public void writeToParcel(Parcel dest, int flags) {
/*  26 */     dest.writeString(this.name);
/*  27 */     dest.writeString(this.address);
/*  28 */     dest.writeString(this.city);
/*  29 */     dest.writeString(this.state);
/*  30 */     dest.writeString(this.country);
/*     */     
/*  32 */     dest.writeString(this.telephone);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int describeContents() {
/*  38 */     return 0;
/*     */   }
/*     */   
/*  41 */   public static final Parcelable.Creator<ShippingAddress> CREATOR = new Parcelable.Creator<ShippingAddress>()
/*     */     {
/*     */       public ShippingAddress createFromParcel(Parcel in) {
/*  44 */         return new ShippingAddress(in);
/*     */       }
/*     */ 
/*     */       
/*     */       public ShippingAddress[] newArray(int size) {
/*  49 */         return new ShippingAddress[size];
/*     */       }
/*     */     };
/*     */   
/*     */   public String getName() {
/*  54 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  58 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getAddress() {
/*  62 */     return this.address;
/*     */   }
/*     */   
/*     */   public void setAddress(String address) {
/*  66 */     this.address = address;
/*     */   }
/*     */   
/*     */   public String getCity() {
/*  70 */     return this.city;
/*     */   }
/*     */   
/*     */   public void setCity(String city) {
/*  74 */     this.city = city;
/*     */   }
/*     */   
/*     */   public String getState() {
/*  78 */     return this.state;
/*     */   }
/*     */   
/*     */   public void setState(String state) {
/*  82 */     this.state = state;
/*     */   }
/*     */   
/*     */   public String getCountry() {
/*  86 */     return this.country;
/*     */   }
/*     */   
/*     */   public void setCountry(String country) {
/*  90 */     this.country = country;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTelephone() {
/*  96 */     return this.telephone;
/*     */   }
/*     */   
/*     */   public void setTelephone(String telephone) {
/* 100 */     this.telephone = telephone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ShippingAddress(Parcel in) {
/* 107 */     this.name = in.readString();
/* 108 */     this.address = in.readString();
/* 109 */     this.city = in.readString();
/* 110 */     this.state = in.readString();
/* 111 */     this.country = in.readString();
/*     */     
/* 113 */     this.telephone = in.readString();
/*     */   }
/*     */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/ShippingAddress.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */