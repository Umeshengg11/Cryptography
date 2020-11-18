import java.security.*;
import java.util.Iterator;
import java.util.Set;

public class Cryptography {
   public void infoProviders(){
       Provider[] providers = Security.getProviders();
       Provider provider;
       for (int i = 0; i < providers.length; i++) {
           provider = providers[i];
           Set<Provider.Service> setService = provider.getServices();
           Iterator<Provider.Service> itServices = setService.iterator();
           Provider.Service service;
           System.out.println(provider.getName()+" "+ provider.getVersionStr() +": "+provider.getInfo());
           while (itServices.hasNext()){
               service = itServices.next();
               System.out.println("  "+service.getAlgorithm()+"  "+service.getType());
           }
           System.out.println();
       }

   }
   public static void main(String[] args) throws NoSuchAlgorithmException {
//       accessing Providers
       Signature signature = null;
       signature = Signature.getInstance("MD5withRSA");
       System.out.println(signature.getProvider());
       //Security.addProvider(new BouncyCastleProvider());
   }

}
