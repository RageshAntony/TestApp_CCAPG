/*    */ package mumbai.dev.sdkdubai;
/*    */ 
/*    */ import android.app.AlertDialog;
/*    */ import android.content.Context;
/*    */ import android.content.DialogInterface;
/*    */ import android.content.Intent;
/*    */ import android.os.Bundle;
/*    */ import android.view.MenuItem;
/*    */ import androidx.appcompat.app.AppCompatActivity;
/*    */ import androidx.appcompat.widget.Toolbar;
/*    */ 
/*    */ public class DialogActivity extends AppCompatActivity {
/*    */   Toolbar mToolbar;
/*    */   
/*    */   protected void onCreate(Bundle savedInstanceState) {
/* 16 */     super.onCreate(savedInstanceState);
/* 17 */     setContentView(R.layout.activity_dialog);
/*    */     
/* 19 */     this.mToolbar = (Toolbar)findViewById(R.id.toolbar);
/* 20 */     initialiseToolbar("Payment Details");
/* 21 */     show_alert(getIntent().getStringExtra("msg"));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initialiseToolbar(String toolbarTitle) {
/* 28 */     setSupportActionBar(this.mToolbar);
/* 29 */     getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/* 30 */     getSupportActionBar().setDisplayShowHomeEnabled(true);
/* 31 */     getSupportActionBar().setTitle(toolbarTitle);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onOptionsItemSelected(MenuItem menuItem) {
/* 36 */     if (menuItem.getItemId() == 16908332) {
/* 37 */       onBackPressed();
/*    */     }
/* 39 */     return super.onOptionsItemSelected(menuItem);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onBackPressed() {
/* 45 */     AlertDialog alertDialog = (new AlertDialog.Builder((Context)this)).create();
/*    */ 
/*    */     
/* 48 */     alertDialog.setMessage("Do you really want to cancel this transaction?");
/*    */     
/* 50 */     alertDialog.setButton(-2, "CANCEL", new DialogInterface.OnClickListener()
/*    */         {
/*    */           public void onClick(DialogInterface dialog, int which) {}
/*    */         });
/*    */ 
/*    */ 
/*    */     
/* 57 */     alertDialog.setButton(-1, "OK", new DialogInterface.OnClickListener()
/*    */         {
/*    */           public void onClick(DialogInterface dialog, int which)
/*    */           {
/* 61 */             DialogActivity.this.finish();
/*    */           }
/*    */         });
/*    */ 
/*    */     
/* 66 */     alertDialog.show();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void show_alert(String msg) {
/* 74 */     AlertDialog alertDialog = (new AlertDialog.Builder((Context)this)).create();
/*    */     
/* 76 */     alertDialog.setTitle("Error!!!");
/*    */     
/* 78 */     alertDialog.setMessage(msg);
/*    */ 
/*    */     
/* 81 */     alertDialog.setButton(-1, "OK", new DialogInterface.OnClickListener()
/*    */         {
/*    */           public void onClick(DialogInterface dialog, int which)
/*    */           {
/* 85 */             Intent i = new Intent((Context)DialogActivity.this, PaymentOptions.class);
/* 86 */             i.putExtra("merchant", PaymentOptions.merchant);
/* 87 */             i.putExtra("billing", PaymentOptions.billing);
/* 88 */             i.putExtra("shipping", PaymentOptions.shipping);
/* 89 */             DialogActivity.this.finish();
/* 90 */             DialogActivity.this.startActivity(i);
/*    */           }
/*    */         });
/*    */ 
/*    */     
/* 95 */     alertDialog.show();
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/DialogActivity.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */