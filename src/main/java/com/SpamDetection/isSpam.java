package com.SpamDetection;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by elf on 03.05.2016.
 */
public class isSpam {

    private JSONObject user = null;
    private JSONObject tmp = null;
    private JSONArray entities = null;
    private String text = "";
    // blacklisti düzenleyebiliriz

    private String blackListW = new String("anal,anus,arse,ass,ballsack,balls,bastard,bitch,biatch,bloody,blowjob,bollock,bollok,boner,boob,bugger,bum,butt,buttplug,clitoris,cock,coon,crap,cunt,damn,dick,dildo,dyke,fag,feck,fellate,fellatio,felching,fuck,fudgepacker,fudge,packer,flange,Goddamn,hell,homo,jerk,jizz,knobend,knob,labia,muff,nigger,nigga,penis,piss,poop,prick,pube,pussy,queer,scrotum,sex,shit,sh1t,slut,smegma,spunk,tit,tosser,turd,twat,vagina,wank,whore");
   // private String blackListW = new String("tünel,oç,sktr,o.ç.,yalan,eklentidunyasi,s.k,s.keyim,s.kiim,sokiim,a.k,a.q,abaza,abuse,ibne,adıma,adımı,alayına,amı,am!na,am'na,anan,angut,apaçiyik,aptal,bacini,bacını,begen,beğe,beğen,bitch,biti,bok,boops,butik,dallama,dangalak,daracık,daşag,daşak,daşaq,dedeler,defol,don,döl,ecdad,ekle,eqle,facebook.com,fahişe,fck you,feriştah,fifi,fuck,gavat,gavur,gerizekalı,gerzek,got,gstring,göt,göt lalesi,hassiktir,hastir,haydar,ibne,inbetor,inci,ismime,kafir,kaka,kaltak,kancık,katil,kokuşmuşlar,kuku,kökü kurusun,köküne,külot,küspe,kıç,laa,lan,lanet,madafaka,malabadi,malafat,malt,manyak,meme,motherfucker,nah,oruspu,orusbu,orospu,orspu,orspı,orıspı,ot,patlicani oksamak,pezevenk,pipi,piç,porsumus,pörsümüş,reklam,s!kerim,s0karım,sahibinden,saksağan,sakso,salak,saxo,sayfa,seks,sevişken,sex,sie,sik,sik kafalı bülbül,siktir git,siktir olsun,skibbe,sokarlar,sokuşmuşlar,son of,sübyan,sübyancı,sülaleni,sütyen,sıkerim,sıkeriz,sıkerım,sıkerız,sıçarım,takip,tanga,tarram,tasak,tasaq,taşak,taşaq,tikayin,tıkla,tikla,vibratör,yalamak,yalarım,yapram,yarak,yarram,yarrak,yavshak,yavuşak,yavşak,yawshak,yevşak,yevşek,yosma,zilli,çiş,çük,çüx,örörpu,ıbne,ırıspı,şerefsiz,şirret,şırfıntı,amuna,www,credits,game,get,piç,orosbu,ananı,sikiyim,piç,pic,puşt,pust,->like,7.claim,free,credit,1.get your free 5000 fb credit <-click here,2.get your free 5000 fb credit <-click here,get your free 5000 fb credit <-click here,get,skym,serefsizler,serefsiz,serefsz,şerefsz,amk,gotler,sıçıyosunuz,siçiyosunuz,sıçtı,ameke,siçti,sicti,anani,mk,oç,hediye,bedava 500000 cip,bĕdâvé cïp>bedave 500,cip>,>>>,badeve,bedave,bêd@vê,1.claim your free 5000 fb game credit,40.claim your free 5000 fb game credit,2.claim your free 5000 fb game credit <-click here,bëdavë,bëdãvë,bëdavë,bëd@vë,bèdávè,1.get,2.get,3.get,4.get,5.get,6.get,7.get,8.get,9.get,10.get,11.get,12.get,13.get,14.get,15.get,16.get,17.get,18.get,19.get,20.get,21.get,22.get,23.get,24.get,25.get,26.get,27.get,28.get,29.get,30.get,31.get,32.get,33.get,34.get,34.get,35.get,36.get,37.get,38.get,39.get,40.get,41.get,42.get,43.get,44.get,45.get,46.get,47.get,48.get,49.get,50.get,51.get,52.get,53.get,54.get,55.get,56.get,4.000.000,1.000.000,bêdàvê,cïp://is.gd,fredeem now! its free,arse,arsehole,ass,axwound,bampot,bastard,beaner,bitch,blowjob,bollocks,bollox,boner,brotherfucker,bullshit,bumblefuck,butt,cameltoe,carpetmuncher,chesticle,chinc,chink,choad,chode,clit,clitface,clitfuck,clusterfuck,cock,coochie,coochy,coon,cooter,cracker,cum,cunnie,cunnilingus,cunt,dago,damn,deggo,dick,dike,dildo,dipshit,doochbag,dookie,douch,dumass,dumbass,dumbass,dumbfuck,dumbshit,dumshit,dyke,fag,fatass,fellatio,feltch,flamer,fudgepacker,homosexual,gay,goddamn,gooch,gook,gringo,guido–italian,handjob,hardon,heeb,hell,homo,honkey,humping,jackass,jagoff,jap,jerkoff,jerkass,jigaboo,jizz,junglebunny,junglebunny,kike,kooch,kootch,kraut,kunt,kyke,lameass,lardass,lesbian,lesbo,lezzie,mcfagget,mick,minge,mothafucka,mothafuckin\',motherfuck,muff,munging,negro,nigaboo,nigga,nigger,niglet,nutsack,paki,panooch,pecker,penis,piss,polesmoker,pollock,poon,porchmonkey,prick,punanny,punta,pussies,pussy,puto–idiot,queef,queer,queerbait,queerhole,renob,rimjob,ruski–russian,sandnigger,sandnigger,schlong,scrote,shit,shiz,skank,skeet,skullfuck,slut,smeg,snatch,spic,splooge,spook,suckass–idiot,tard,testicle,thundercunt,tit,twat,unclefucker,vag,vagina,vajayjay,vjayjay,wank,wetback,whore,wop,anus,facedecul,trouducul,troudeballe,abruti,tafiole,folle,clown,bouffon,trousducul,lopette,connard,pédale,suceur,lécheurdecul,assmonkey,casse-burne,tronchedecul,enfoirédenégro,enfoiré,dégénéré,vagin,idiot,bâtard,chicano,chienne,chiennes,bitchtits,vache,fellation,pipe,couilles,testicules,gaule,connerie,enculeurd\'homo,marguerite,poitrine,chinetoque,bridé,queue,bistouquette,clitoris,facedeclito,enchaînementdeconneries,bite,facedebite,facedepénis,gland,pédé,facedesinge,boutdebite,suceurdebite,foufoune,afro-américain,minette,jouir,poufiasse,minou,cunnilingus,moule,troudefion,facedechatte,lécheusedechatte,rital,merde,spaghetti,pénis,facedequeue,enculeurdepédé,facedephallus,sperme,zale,bites,lécheurdebite,suçagedebite,imbécile,couillon,crétin,andouille,gode,abrutidegay,tantouse,tapette,tante,grospleindesoupe,suçagedequeue,niquer,tronchedebite,baisé,baiseur,facedeniqué,têtedenoeud,baiser,putain,abrutidemerde,pine,gay,baiseurdepédé,putaindemerde,gringo,branlette,trique,youpin,homo,blanc-bec,taré,jap,éjaculer,branleur,purée,abricot,perdant,grossac,lesbienne,lesbo,enculédesamère,négro,nègre,blackos,nègres,mancheàbalai,suceurdepénis,pisser,énervé,polack,chattes,chatte,léchagedechatte,lécheurdecouilles,barreau,léchaged\'anus,verge,caca,tronchedemerde,sacàmerde,facedecaca,trouàmerde,chieur,leplusmerdique,chier,cochonne,salope,sacàfoutre,attardé,burne,sein,branletteespagnole,seins,foufounette,garageàbites,facedesperme,macaroni,analelsker,analkjører,analkjørere,anus,bæsj,ballene,baller,ballesekk,bastard,blowjob,blowjobs,bullshit,cameltoe,dass,dildo,dratilhelvete,drit,drit,driter,dritt,dritt,dritt,drittånde,dritter,drittfitte,drittflekk,dritthøl,dritthus,drittkuk,drittpikk,drittryne,drittsekk,drittsekk,drittsekk,drittsekk,drittsekk,drittsekken,drittsekker,drittsekker,drittvaffel,dust,etknull,faen,faenheller,faens,feiting,fitta,fitte,fuckup,gartner,guido,guling,helvete,homo,homse,hore,hviting,idiot,jæklajøde,jævlajøde,jævlamorapuler,jævligforbanna,japse,jizz,jødsel,jokking,kagger,klit,knuller,komme,kuk,lesbe,lespe,lesbene,lespene,lesber,lesper,mchomse,meksikanikke,mestdritt,morapuler,morapuling,nazist,neger,nigger,onkelknuller,pakkis,pastaeter,pastaeter,penis,pikk,piss,polakk,potet,pule,pulte,ræv,rass,rimjob,rompeape,rompeplugg,runk,sæd,sandneger,shithode,shitspanjoler,shittryne,sjenka,skalleknull,sklætte,skløtte,sleiker,sparket,sperm,ståpikk,suging,slikking,svartejævel,svarting,taper,tard,testikkel,tispe,titfuck,tits,titt,tøs,ultrafitte,ultrahomo,ungdomsfitte,utlending,vagina,vodkaelsker,voksenfitte,abortsmitare,acnofag,afrikanrunk,akurunktur,alko-pung,alpfitta,alphora,anal,antirunkcreme,anus,aprilfitta,arbetar-runk,arbetarsex,ärkebög,ärkeknull,armébög,asstigmatism,auschwitzmökare,autofellatio,autofil,autosniper,avelskav,avföringskudde,avköningsvälkomnade,avslöjare,avsmygerska,avsugningburka,babian-anus,babianröv,baconballe,baconrosrekyl,baguettebög,bajdoms-bobby,bajsamera,bajsbergsbyggare,bajsbook,bajsbröder,bajsbröder,bajsbrygga,bajsdildo,bajsfest,bajsförnedring,bajsfötter,bajsgips,bajshatt,bajskork,bajsmacka,bajsmannen,bajspackare,bajspärla,bajspassare,bajsvälsignelse,bakmus,bakrånare,bakteriebög,bakteriedopp,bakterie-runk,bakvärk,ballhojta,bangbros,banjobanta,barbagay,barkbåt,barmhärtighetsknull,barnboksanalfixering,barstolsbög,bassängsäda,bastuballe,bastukorv,basturace,batmanfitta,batongluder,bautadasen,bautakuk,bäverdräparn,bäverjakt,bäverjording,bävernäve,bea-fitta,bergsslyna,bertofili,credits,crédíts,crédìt$,ᴄʀᴇᴅɪᴛs,bögporr-marato");
    private String[] blackList = blackListW.split(",");

    public isSpam(JSONObject jsonObject, ArrayList<String> TweetValues){

        if(jsonObject != null){
            try {
                user = jsonObject.getJSONObject("user");
                tmp = jsonObject.getJSONObject("entities");

                int lengthOfProfileName = (user.get("screen_name").toString().length());
                int lenghtOfProfileDescription = (user.get("description").toString().length());
                int numberOfFollowings = ((int)user.get("friends_count"));
                int numberOfFollowers = ((int)user.get("followers_count"));
                int numberOfStatusesCount = ((int)user.get("statuses_count"));
                int userCreatedAge = TwitterDateParser.parseTwitterUTC((String) user.get("created_at"));

                double ratioOfNumberOfFE_FI=0, reputatitonOfUser=0, followingRate=0;

                if(numberOfFollowings == 0){
                    ratioOfNumberOfFE_FI = Integer.MAX_VALUE;
                    if(numberOfFollowers == 0)
                        reputatitonOfUser = 0;
                }
                else{
                    ratioOfNumberOfFE_FI = Math.round((((double)numberOfFollowers)/((double)numberOfFollowings))* 100.0) / 100.0;
                    reputatitonOfUser = Math.round(((double)numberOfFollowers) / ((numberOfFollowers + numberOfFollowings)) * 100.0) / 100.0;
                }

                if(userCreatedAge == 0)
                    followingRate = Integer.MAX_VALUE;
                else
                    followingRate = Math.round(((double)numberOfFollowings/((double)userCreatedAge)) * 100.0) / 100.0;

                /*
                ratioOfNumberOfFE_FI = Math.round((((double)numberOfFollowers+1.0)/((double)numberOfFollowings+1.0))* 100.0) / 100.0;
                double reputatitonOfUser = Math.round(((double)numberOfFollowers+1.0) / ((numberOfFollowers + numberOfFollowings+1.0)) * 100.0) / 100.0;
                double followingRate = Math.round((((double)numberOfFollowings+1.0)/((double)userCreatedAge+1.0)) * 100.0) / 100.0;
                * */
                text = jsonObject.get("text").toString();

                int lenghtOfText = text.length();
                entities = (JSONArray) tmp.get("hashtags");
                int hashtagCount = entities.length();

                entities = (JSONArray) tmp.get("urls");
                int urlsCount = entities.length();
                entities = (JSONArray) tmp.get("user_mentions");
                int mentionCount = entities.length();

                /*****          CONTENT             *****/
                int numberOfCharacters = text.length();
                int numberOfWords=1;
                int numberOfCapChar=0;
                int meanWordLenght=0;

                int numberOfExclamation=0, numberOfQuestionMark=0;
               // for(int i=0; i<text.length(); ++i)

                for(int i=0; i<text.length(); ++i)
                {
                    if(text.charAt(i) == ' ')
                        ++numberOfWords;
                    if(text.charAt(i) == '?')
                        ++numberOfQuestionMark;
                    if(text.charAt(i) == '!')
                        ++numberOfExclamation;
                    if(text.charAt(i) >= 'A' && text.charAt(i) <= 'Z')
                        ++numberOfCapChar;

                }
                text = text.replace("\\s", " ");

                int maxWordLenght=0;
                String[] textContent = text.split(" ");
                for(int i=0; i<textContent.length; ++i){
                    if(textContent[i].length() > maxWordLenght)
                        maxWordLenght = textContent[i].length();
                }

                numberOfWords = numberOfWords - (hashtagCount + urlsCount + mentionCount);
                double urlPerWord, mentionPerWord, CapPerWord, HashtagPerWord;
                if(numberOfWords == 0){
                    urlPerWord = Integer.MAX_VALUE;
                    mentionPerWord = Integer.MAX_VALUE;
                    CapPerWord = Integer.MAX_VALUE;
                    HashtagPerWord = Integer.MAX_VALUE;
                }else{
                    urlPerWord = Math.round(((double)urlsCount / (numberOfWords)) * 100.0) / 100.0 ;
                    mentionPerWord = Math.round(((double)mentionCount / (numberOfWords)) * 100.0) / 100.0 ;
                    CapPerWord = Math.round(((double)numberOfCapChar / (numberOfWords)) * 100.0) / 100.0 ;
                    HashtagPerWord = Math.round(((double)hashtagCount / (numberOfWords)) * 100.0) / 100.0 ;

                }



                int numberOfSpamWord=0;
                for(int i=0; i<blackList.length; ++i){

                    // extra kontrolleri birleştirdim - hem blacklist hem hem fuuuu*ck, $ıllık, $illiiik gibi ifadeleri yakalıyor.
                    if(text.contains(blackList[i]) || text.contains(blackList[i].replace("([^A-Za-z0-9öçşığüÖÇŞİĞÜ,\\s])", "\\$1+")) ||
                            text.contains(blackList[i].replace("([0-9a-zA-ZöçşığüÖÇŞİĞÜ,\\s])", "$1+")) || text.contains(blackList[i].replace("([0-9a-zA-ZöçşığüÖÇŞİĞÜ,\\s])", "$1+")) ||
                            text.contains(blackList[i].replace("[ĞğGg]", "[ĞğGg]")) || text.contains(blackList[i].replace("[ÜüUu]", "[ÜüUu]")) ||
                            text.contains(blackList[i].replace("[Iİıi1l]", "[Iİıi1l]")) || text.contains(blackList[i].replace("[OÖoö]", "[OÖoö]")) ||
                            text.contains(blackList[i].replace("[SsŞş$]", "[SsŞş$]")) || text.contains(blackList[i].replace("[Aa@4]", "[Aa@4]")) ||
                            text.contains(blackList[i].replace("[Ee€]", "[Ee€]")) || text.contains(blackList[i].replace("[Bbß]", "[Bbß]"))) {

                            //System.out.print("BLABLAAAAA:   " + blackList[i] + "  ");
                            ++numberOfSpamWord;
                        }
                }
                double SpamPerWord = Math.round(((double)numberOfSpamWord / (numberOfWords+1)) * 100.0) / 100.0 ;


                /*
                System.out.println("lengthOfProfileName: " + lengthOfProfileName + "  " +
                                   "lenghtOfProfileDescription: " + lenghtOfProfileDescription + "  " +
                                   "numberOfFollowings: " + numberOfFollowings + "  " +
                                   "numberOfFollowers: " + numberOfFollowers + "  " +
                                   "numberOfStatusesCount: " + numberOfStatusesCount + "  " +
                                   "userCreatedAge: " + userCreatedAge + "  \n" +
                                   "ratioOfNumberOfFE_FI: " + ratioOfNumberOfFE_FI + "  " +
                                   "reputatitonOfUser: " + reputatitonOfUser + "  " +
                                   "followingRate: " + followingRate + " \n" +
                                   "text: " + text + " \n" +
                                   "hashtagCount: " + hashtagCount + " " +
                                   "numberOfWords: " + numberOfWords + " " +
                                   "numberOfCharacters: " + numberOfCharacters + " " +
                                   "numberOfCapChar: " + numberOfCapChar + " \n" +
                                   "urlsCount: " + urlsCount + "  " +
                                   "urlPerWord: " + urlPerWord + "  " +
                                   "mentionCount: " + mentionCount + "  " +
                                   "mentionPerWord: " + mentionPerWord + " " +
                                   "maxWordLenght: " + maxWordLenght + " " +
                                   "numberOfExclamation: " + numberOfExclamation + " " +
                                   "numberOfQuestionMark: " + numberOfQuestionMark + " " +
                                   "numberOfSpamWord: " + numberOfSpamWord + " " +
                                   "CapPerWord: " + CapPerWord + " " +

                                 "\n"
                );*/


                System.out.println( ratioOfNumberOfFE_FI + " " + numberOfFollowers + " " + numberOfFollowings);



                String tweetValues = new String(lengthOfProfileName + "," + lenghtOfProfileDescription + "," +  numberOfFollowings + "," + numberOfFollowers + "," +
                                         numberOfStatusesCount + "," + userCreatedAge + "," + ratioOfNumberOfFE_FI + "," + reputatitonOfUser + "," + followingRate + "," +
                                         numberOfWords + "," + numberOfCharacters + "," + numberOfCapChar + "," + CapPerWord + "," + maxWordLenght + "," + meanWordLenght + "," +
                                         numberOfExclamation + "," + numberOfQuestionMark + "," + urlsCount + "," + urlPerWord + "," + hashtagCount + "," + HashtagPerWord + "," +
                                         mentionCount + "," + mentionPerWord + "," + numberOfSpamWord + "," + SpamPerWord + ",%%%%%%%%%" + text + "\n" );

                TweetValues.add(tweetValues);
                //System.out.print(tweetValues);

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

}
