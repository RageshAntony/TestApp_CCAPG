/*     */ package mumbai.dev.sdkdubai;
/*     */ 
/*     */ import android.os.Parcel;
/*     */ import android.os.Parcelable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StandardInstructions
/*     */   implements Parcelable
/*     */ {
/*     */   String si_type;
/*     */   String si_mer_ref_no;
/*     */   String si_is_setup_amt;
/*     */   String si_amount;
/*     */   String si_start_date;
/*     */   String si_frequency_type;
/*     */   String si_frequency;
/*     */   String si_bill_cycle;
/*     */   
/*     */   public StandardInstructions() {}
/*     */   
/*     */   protected StandardInstructions(Parcel in) {
/*  23 */     this.si_type = in.readString();
/*  24 */     this.si_mer_ref_no = in.readString();
/*  25 */     this.si_is_setup_amt = in.readString();
/*  26 */     this.si_amount = in.readString();
/*  27 */     this.si_start_date = in.readString();
/*  28 */     this.si_frequency_type = in.readString();
/*  29 */     this.si_frequency = in.readString();
/*  30 */     this.si_bill_cycle = in.readString();
/*     */   }
/*     */   
/*  33 */   public static final Parcelable.Creator<StandardInstructions> CREATOR = new Parcelable.Creator<StandardInstructions>()
/*     */     {
/*     */       public StandardInstructions createFromParcel(Parcel in) {
/*  36 */         return new StandardInstructions(in);
/*     */       }
/*     */ 
/*     */       
/*     */       public StandardInstructions[] newArray(int size) {
/*  41 */         return new StandardInstructions[size];
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   public int describeContents() {
/*  47 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeToParcel(Parcel parcel, int i) {
/*  52 */     parcel.writeString(this.si_type);
/*  53 */     parcel.writeString(this.si_mer_ref_no);
/*  54 */     parcel.writeString(this.si_is_setup_amt);
/*  55 */     parcel.writeString(this.si_amount);
/*  56 */     parcel.writeString(this.si_start_date);
/*  57 */     parcel.writeString(this.si_frequency_type);
/*  58 */     parcel.writeString(this.si_frequency);
/*  59 */     parcel.writeString(this.si_bill_cycle);
/*     */   }
/*     */   
/*     */   public String getSi_type() {
/*  63 */     return this.si_type;
/*     */   }
/*     */   
/*     */   public void setSi_type(String si_type) {
/*  67 */     this.si_type = si_type;
/*     */   }
/*     */   
/*     */   public String getSi_mer_ref_no() {
/*  71 */     return this.si_mer_ref_no;
/*     */   }
/*     */   
/*     */   public void setSi_mer_ref_no(String si_mer_ref_no) {
/*  75 */     this.si_mer_ref_no = si_mer_ref_no;
/*     */   }
/*     */   
/*     */   public String getSi_is_setup_amt() {
/*  79 */     return this.si_is_setup_amt;
/*     */   }
/*     */   
/*     */   public void setSi_is_setup_amt(String si_is_setup_amt) {
/*  83 */     this.si_is_setup_amt = si_is_setup_amt;
/*     */   }
/*     */   
/*     */   public String getSi_amount() {
/*  87 */     return this.si_amount;
/*     */   }
/*     */   
/*     */   public void setSi_amount(String si_amount) {
/*  91 */     this.si_amount = si_amount;
/*     */   }
/*     */   
/*     */   public String getSi_start_date() {
/*  95 */     return this.si_start_date;
/*     */   }
/*     */   
/*     */   public void setSi_start_date(String si_start_date) {
/*  99 */     this.si_start_date = si_start_date;
/*     */   }
/*     */   
/*     */   public String getSi_frequency_type() {
/* 103 */     return this.si_frequency_type;
/*     */   }
/*     */   
/*     */   public void setSi_frequency_type(String si_frequency_type) {
/* 107 */     this.si_frequency_type = si_frequency_type;
/*     */   }
/*     */   
/*     */   public String getSi_frequency() {
/* 111 */     return this.si_frequency;
/*     */   }
/*     */   
/*     */   public void setSi_frequency(String si_frequency) {
/* 115 */     this.si_frequency = si_frequency;
/*     */   }
/*     */   
/*     */   public String getSi_bill_cycle() {
/* 119 */     return this.si_bill_cycle;
/*     */   }
/*     */   
/*     */   public void setSi_bill_cycle(String si_bill_cycle) {
/* 123 */     this.si_bill_cycle = si_bill_cycle;
/*     */   }
/*     */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/StandardInstructions.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */