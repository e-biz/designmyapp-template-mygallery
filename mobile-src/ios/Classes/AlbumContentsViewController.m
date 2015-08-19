/*
     File: AlbumContentsViewController.m
 Abstract: View controller to manaage displaying the contents of an album.
  Version: 1.1
 
 Disclaimer: IMPORTANT:  This Apple software is supplied to you by Apple
 Inc. ("Apple") in consideration of your agreement to the following
 terms, and your use, installation, modification or redistribution of
 this Apple software constitutes acceptance of these terms.  If you do
 not agree with these terms, please do not use, install, modify or
 redistribute this Apple software.
 
 In consideration of your agreement to abide by the following terms, and
 subject to these terms, Apple grants you a personal, non-exclusive
 license, under Apple's copyrights in this original Apple software (the
 "Apple Software"), to use, reproduce, modify and redistribute the Apple
 Software, with or without modifications, in source and/or binary forms;
 provided that if you redistribute the Apple Software in its entirety and
 without modifications, you must retain this notice and the following
 text and disclaimers in all such redistributions of the Apple Software.
 Neither the name, trademarks, service marks or logos of Apple Inc. may
 be used to endorse or promote products derived from the Apple Software
 without specific prior written permission from Apple.  Except as
 expressly stated in this notice, no other rights or licenses, express or
 implied, are granted by Apple herein, including but not limited to any
 patent rights that may be infringed by your derivative works or by other
 works in which the Apple Software may be incorporated.
 
 The Apple Software is provided by Apple on an "AS IS" basis.  APPLE
 MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION
 THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS
 FOR A PARTICULAR PURPOSE, REGARDING THE APPLE SOFTWARE OR ITS USE AND
 OPERATION ALONE OR IN COMBINATION WITH YOUR PRODUCTS.
 
 IN NO EVENT SHALL APPLE BE LIABLE FOR ANY SPECIAL, INDIRECT, INCIDENTAL
 OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 INTERRUPTION) ARISING IN ANY WAY OUT OF THE USE, REPRODUCTION,
 MODIFICATION AND/OR DISTRIBUTION OF THE APPLE SOFTWARE, HOWEVER CAUSED
 AND WHETHER UNDER THEORY OF CONTRACT, TORT (INCLUDING NEGLIGENCE),
 STRICT LIABILITY OR OTHERWISE, EVEN IF APPLE HAS BEEN ADVISED OF THE
 POSSIBILITY OF SUCH DAMAGE.
 
 Copyright (C) 2013 Apple Inc. All Rights Reserved.
 
 */

#import "AlbumContentsViewController.h"
#import "MyPageViewController.h"
#import "PageViewControllerData.h"

#import "DMAProperties.h"

#import <AFNetworking/AFNetworking.h>

NSString* defaultStringUrl[100]= {@"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg",
    @"https://lh4.googleusercontent.com/--dq8niRp7W4/URquVgmXvgI/AAAAAAAAAbs/-gnuLQfNnBA/s1024/A%252520Song%252520of%252520Ice%252520and%252520Fire.jpg",
    @"https://lh5.googleusercontent.com/-7qZeDtRKFKc/URquWZT1gOI/AAAAAAAAAbs/hqWgteyNXsg/s1024/Another%252520Rockaway%252520Sunset.jpg",
    @"https://lh3.googleusercontent.com/--L0Km39l5J8/URquXHGcdNI/AAAAAAAAAbs/3ZrSJNrSomQ/s1024/Antelope%252520Butte.jpg",
    @"https://lh6.googleusercontent.com/-8HO-4vIFnlw/URquZnsFgtI/AAAAAAAAAbs/WT8jViTF7vw/s1024/Antelope%252520Hallway.jpg",
    @"https://lh4.googleusercontent.com/-WIuWgVcU3Qw/URqubRVcj4I/AAAAAAAAAbs/YvbwgGjwdIQ/s1024/Antelope%252520Walls.jpg",
    @"https://lh6.googleusercontent.com/-UBmLbPELvoQ/URqucCdv0kI/AAAAAAAAAbs/IdNhr2VQoQs/s1024/Apre%2525CC%252580s%252520la%252520Pluie.jpg",
    @"https://lh3.googleusercontent.com/-s-AFpvgSeew/URquc6dF-JI/AAAAAAAAAbs/Mt3xNGRUd68/s1024/Backlit%252520Cloud.jpg",
    @"https://lh5.googleusercontent.com/-bvmif9a9YOQ/URquea3heHI/AAAAAAAAAbs/rcr6wyeQtAo/s1024/Bee%252520and%252520Flower.jpg",
    @"https://lh5.googleusercontent.com/-n7mdm7I7FGs/URqueT_BT-I/AAAAAAAAAbs/9MYmXlmpSAo/s1024/Bonzai%252520Rock%252520Sunset.jpg",
    @"https://lh6.googleusercontent.com/-4CN4X4t0M1k/URqufPozWzI/AAAAAAAAAbs/8wK41lg1KPs/s1024/Caterpillar.jpg",
    @"https://lh3.googleusercontent.com/-rrFnVC8xQEg/URqufdrLBaI/AAAAAAAAAbs/s69WYy_fl1E/s1024/Chess.jpg",
    @"https://lh5.googleusercontent.com/-WVpRptWH8Yw/URqugh-QmDI/AAAAAAAAAbs/E-MgBgtlUWU/s1024/Chihuly.jpg",
    @"https://lh5.googleusercontent.com/-0BDXkYmckbo/URquhKFW84I/AAAAAAAAAbs/ogQtHCTk2JQ/s1024/Closed%252520Door.jpg",
    @"https://lh3.googleusercontent.com/-PyggXXZRykM/URquh-kVvoI/AAAAAAAAAbs/hFtDwhtrHHQ/s1024/Colorado%252520River%252520Sunset.jpg",
    @"https://lh3.googleusercontent.com/-ZAs4dNZtALc/URquikvOCWI/AAAAAAAAAbs/DXz4h3dll1Y/s1024/Colors%252520of%252520Autumn.jpg",
    @"https://lh4.googleusercontent.com/-GztnWEIiMz8/URqukVCU7bI/AAAAAAAAAbs/jo2Hjv6MZ6M/s1024/Countryside.jpg",
    @"https://lh4.googleusercontent.com/-bEg9EZ9QoiM/URquklz3FGI/AAAAAAAAAbs/UUuv8Ac2BaE/s1024/Death%252520Valley%252520-%252520Dunes.jpg",
    @"https://lh6.googleusercontent.com/-ijQJ8W68tEE/URqulGkvFEI/AAAAAAAAAbs/zPXvIwi_rFw/s1024/Delicate%252520Arch.jpg",
    @"https://lh5.googleusercontent.com/-Oh8mMy2ieng/URqullDwehI/AAAAAAAAAbs/TbdeEfsaIZY/s1024/Despair.jpg",
    @"https://lh5.googleusercontent.com/-gl0y4UiAOlk/URqumC_KjBI/AAAAAAAAAbs/PM1eT7dn4oo/s1024/Eagle%252520Fall%252520Sunrise.jpg",
    @"https://lh3.googleusercontent.com/-hYYHd2_vXPQ/URqumtJa9eI/AAAAAAAAAbs/wAalXVkbSh0/s1024/Electric%252520Storm.jpg",
    @"https://lh5.googleusercontent.com/-PyY_yiyjPTo/URqunUOhHFI/AAAAAAAAAbs/azZoULNuJXc/s1024/False%252520Kiva.jpg",
    @"https://lh6.googleusercontent.com/-PYvLVdvXywk/URqunwd8hfI/AAAAAAAAAbs/qiMwgkFvf6I/s1024/Fitzgerald%252520Streaks.jpg",
    @"https://lh4.googleusercontent.com/-KIR_UobIIqY/URquoCZ9SlI/AAAAAAAAAbs/Y4d4q8sXu4c/s1024/Foggy%252520Sunset.jpg",
    @"https://lh6.googleusercontent.com/-9lzOk_OWZH0/URquoo4xYoI/AAAAAAAAAbs/AwgzHtNVCwU/s1024/Frantic.jpg",
    @"https://lh3.googleusercontent.com/-0X3JNaKaz48/URqupH78wpI/AAAAAAAAAbs/lHXxu_zbH8s/s1024/Golden%252520Gate%252520Afternoon.jpg",
    @"https://lh6.googleusercontent.com/-95sb5ag7ABc/URqupl95RDI/AAAAAAAAAbs/g73R20iVTRA/s1024/Golden%252520Gate%252520Fog.jpg",
    @"https://lh3.googleusercontent.com/-JB9v6rtgHhk/URqup21F-zI/AAAAAAAAAbs/64Fb8qMZWXk/s1024/Golden%252520Grass.jpg",
    @"https://lh4.googleusercontent.com/-EIBGfnuLtII/URquqVHwaRI/AAAAAAAAAbs/FA4McV2u8VE/s1024/Grand%252520Teton.jpg",
    @"https://lh4.googleusercontent.com/-WoMxZvmN9nY/URquq1v2AoI/AAAAAAAAAbs/grj5uMhL6NA/s1024/Grass%252520Closeup.jpg",
    @"https://lh3.googleusercontent.com/-6hZiEHXx64Q/URqurxvNdqI/AAAAAAAAAbs/kWMXM3o5OVI/s1024/Green%252520Grass.jpg",
    @"https://lh5.googleusercontent.com/-6LVb9OXtQ60/URquteBFuKI/AAAAAAAAAbs/4F4kRgecwFs/s1024/Hanging%252520Leaf.jpg",
    @"https://lh4.googleusercontent.com/-zAvf__52ONk/URqutT_IuxI/AAAAAAAAAbs/D_bcuc0thoU/s1024/Highway%2525201.jpg",
    @"https://lh6.googleusercontent.com/-H4SrUg615rA/URquuL27fXI/AAAAAAAAAbs/4aEqJfiMsOU/s1024/Horseshoe%252520Bend%252520Sunset.jpg",
    @"https://lh4.googleusercontent.com/-JhFi4fb_Pqw/URquuX-QXbI/AAAAAAAAAbs/IXpYUxuweYM/s1024/Horseshoe%252520Bend.jpg",
    @"https://lh5.googleusercontent.com/-UGgssvFRJ7g/URquueyJzGI/AAAAAAAAAbs/yYIBlLT0toM/s1024/Into%252520the%252520Blue.jpg",
    @"https://lh3.googleusercontent.com/-CH7KoupI7uI/URquu0FF__I/AAAAAAAAAbs/R7GDmI7v_G0/s1024/Jelly%252520Fish%2525202.jpg",
    @"https://lh4.googleusercontent.com/-pwuuw6yhg8U/URquvPxR3FI/AAAAAAAAAbs/VNGk6f-tsGE/s1024/Jelly%252520Fish%2525203.jpg",
    @"https://lh5.googleusercontent.com/-GoUQVw1fnFw/URquv6xbC0I/AAAAAAAAAbs/zEUVTQQ43Zc/s1024/Kauai.jpg",
    @"https://lh6.googleusercontent.com/-8QdYYQEpYjw/URquwvdh88I/AAAAAAAAAbs/cktDy-ysfHo/s1024/Kyoto%252520Sunset.jpg",
    @"https://lh4.googleusercontent.com/-vPeekyDjOE0/URquwzJ28qI/AAAAAAAAAbs/qxcyXULsZrg/s1024/Lake%252520Tahoe%252520Colors.jpg",
    @"https://lh4.googleusercontent.com/-xBPxWpD4yxU/URquxWHk8AI/AAAAAAAAAbs/ARDPeDYPiMY/s1024/Lava%252520from%252520the%252520Sky.jpg",
    @"https://lh3.googleusercontent.com/-897VXrJB6RE/URquxxxd-5I/AAAAAAAAAbs/j-Cz4T4YvIw/s1024/Leica%25252050mm%252520Summilux.jpg",
    @"https://lh5.googleusercontent.com/-qSJ4D4iXzGo/URquyDWiJ1I/AAAAAAAAAbs/k2pBXeWehOA/s1024/Leica%25252050mm%252520Summilux.jpg",
    @"https://lh6.googleusercontent.com/-dwlPg83vzLg/URquylTVuFI/AAAAAAAAAbs/G6SyQ8b4YsI/s1024/Leica%252520M8%252520%252528Front%252529.jpg",
    @"https://lh3.googleusercontent.com/-R3_EYAyJvfk/URquzQBv8eI/AAAAAAAAAbs/b9xhpUM3pEI/s1024/Light%252520to%252520Sand.jpg",
    @"https://lh3.googleusercontent.com/-fHY5h67QPi0/URqu0Cp4J1I/AAAAAAAAAbs/0lG6m94Z6vM/s1024/Little%252520Bit%252520of%252520Paradise.jpg",
    @"https://lh5.googleusercontent.com/-TzF_LwrCnRM/URqu0RddPOI/AAAAAAAAAbs/gaj2dLiuX0s/s1024/Lone%252520Pine%252520Sunset.jpg",
    @"https://lh3.googleusercontent.com/-4HdpJ4_DXU4/URqu046dJ9I/AAAAAAAAAbs/eBOodtk2_uk/s1024/Lonely%252520Rock.jpg",
    @"https://lh6.googleusercontent.com/-erbF--z-W4s/URqu1ajSLkI/AAAAAAAAAbs/xjDCDO1INzM/s1024/Longue%252520Vue.jpg",
    @"https://lh6.googleusercontent.com/-0CXJRdJaqvc/URqu1opNZNI/AAAAAAAAAbs/PFB2oPUU7Lk/s1024/Look%252520Me%252520in%252520the%252520Eye.jpg",
    @"https://lh3.googleusercontent.com/-D_5lNxnDN6g/URqu2Tk7HVI/AAAAAAAAAbs/p0ddca9W__Y/s1024/Lost%252520in%252520a%252520Field.jpg",
    @"https://lh6.googleusercontent.com/-flsqwMrIk2Q/URqu24PcmjI/AAAAAAAAAbs/5ocIH85XofM/s1024/Marshall%252520Beach%252520Sunset.jpg",
    @"https://lh4.googleusercontent.com/-Y4lgryEVTmU/URqu28kG3gI/AAAAAAAAAbs/OjXpekqtbJ4/s1024/Mono%252520Lake%252520Blue.jpg",
    @"https://lh4.googleusercontent.com/-AaHAJPmcGYA/URqu3PIldHI/AAAAAAAAAbs/lcTqk1SIcRs/s1024/Monument%252520Valley%252520Overlook.jpg",
    @"https://lh4.googleusercontent.com/-vKxfdQ83dQA/URqu31Yq_BI/AAAAAAAAAbs/OUoGk_2AyfM/s1024/Moving%252520Rock.jpg",
    @"https://lh5.googleusercontent.com/-CG62QiPpWXg/URqu4ia4vRI/AAAAAAAAAbs/0YOdqLAlcAc/s1024/Napali%252520Coast.jpg",
    @"https://lh6.googleusercontent.com/-wdGrP5PMmJQ/URqu5PZvn7I/AAAAAAAAAbs/m0abEcdPXe4/s1024/One%252520Wheel.jpg",
    @"https://lh6.googleusercontent.com/-6WS5DoCGuOA/URqu5qx1UgI/AAAAAAAAAbs/giMw2ixPvrY/s1024/Open%252520Sky.jpg",
    @"https://lh6.googleusercontent.com/-u8EHKj8G8GQ/URqu55sM6yI/AAAAAAAAAbs/lIXX_GlTdmI/s1024/Orange%252520Sunset.jpg",
    @"https://lh6.googleusercontent.com/-74Z5qj4bTDE/URqu6LSrJrI/AAAAAAAAAbs/XzmVkw90szQ/s1024/Orchid.jpg",
    @"https://lh6.googleusercontent.com/-lEQE4h6TePE/URqu6t_lSkI/AAAAAAAAAbs/zvGYKOea_qY/s1024/Over%252520there.jpg",
    @"https://lh5.googleusercontent.com/-cauH-53JH2M/URqu66v_USI/AAAAAAAAAbs/EucwwqclfKQ/s1024/Plumes.jpg",
    @"https://lh3.googleusercontent.com/-eDLT2jHDoy4/URqu7axzkAI/AAAAAAAAAbs/iVZE-xJ7lZs/s1024/Rainbokeh.jpg",
    @"https://lh5.googleusercontent.com/-j1NLqEFIyco/URqu8L1CGcI/AAAAAAAAAbs/aqZkgX66zlI/s1024/Rainbow.jpg",
    @"https://lh5.googleusercontent.com/-DRnqmK0t4VU/URqu8XYN9yI/AAAAAAAAAbs/LgvF_592WLU/s1024/Rice%252520Fields.jpg",
    @"https://lh3.googleusercontent.com/-hwh1v3EOGcQ/URqu8qOaKwI/AAAAAAAAAbs/IljRJRnbJGw/s1024/Rockaway%252520Fire%252520Sky.jpg",
    @"https://lh5.googleusercontent.com/-wjV6FQk7tlk/URqu9jCQ8sI/AAAAAAAAAbs/RyYUpdo-c9o/s1024/Rockaway%252520Flow.jpg",
    @"https://lh6.googleusercontent.com/-6cAXNfo7D20/URqu-BdzgPI/AAAAAAAAAbs/OmsYllzJqwo/s1024/Rockaway%252520Sunset%252520Sky.jpg",
    @"https://lh3.googleusercontent.com/-sl8fpGPS-RE/URqu_BOkfgI/AAAAAAAAAbs/Dg2Fv-JxOeg/s1024/Russian%252520Ridge%252520Sunset.jpg",
    @"https://lh6.googleusercontent.com/-gVtY36mMBIg/URqu_q91lkI/AAAAAAAAAbs/3CiFMBcy5MA/s1024/Rust%252520Knot.jpg",
    @"https://lh6.googleusercontent.com/-GHeImuHqJBE/URqu_FKfVLI/AAAAAAAAAbs/axuEJeqam7Q/s1024/Sailing%252520Stones.jpg",
    @"https://lh3.googleusercontent.com/-hBbYZjTOwGc/URqu_ycpIrI/AAAAAAAAAbs/nAdJUXnGJYE/s1024/Seahorse.jpg",
    @"https://lh3.googleusercontent.com/-Iwi6-i6IexY/URqvAYZHsVI/AAAAAAAAAbs/5ETWl4qXsFE/s1024/Shinjuku%252520Street.jpg",
    @"https://lh6.googleusercontent.com/-amhnySTM_MY/URqvAlb5KoI/AAAAAAAAAbs/pFCFgzlKsn0/s1024/Sierra%252520Heavens.jpg",
    @"https://lh5.googleusercontent.com/-dJgjepFrYSo/URqvBVJZrAI/AAAAAAAAAbs/v-F5QWpYO6s/s1024/Sierra%252520Sunset.jpg",
    @"https://lh4.googleusercontent.com/-Z4zGiC5nWdc/URqvBdEwivI/AAAAAAAAAbs/ZRZR1VJ84QA/s1024/Sin%252520Lights.jpg",
    @"https://lh4.googleusercontent.com/-_0cYiWW8ccY/URqvBz3iM4I/AAAAAAAAAbs/9N_Wq8MhLTY/s1024/Starry%252520Lake.jpg",
    @"https://lh3.googleusercontent.com/-A9LMoRyuQUA/URqvCYx_JoI/AAAAAAAAAbs/s7sde1Bz9cI/s1024/Starry%252520Night.jpg",
    @"https://lh3.googleusercontent.com/-KtLJ3k858eY/URqvC_2h_bI/AAAAAAAAAbs/zzEBImwDA_g/s1024/Stream.jpg",
    @"https://lh5.googleusercontent.com/-dFB7Lad6RcA/URqvDUftwWI/AAAAAAAAAbs/BrhoUtXTN7o/s1024/Strip%252520Sunset.jpg",
    @"https://lh5.googleusercontent.com/-at6apgFiN20/URqvDyffUZI/AAAAAAAAAbs/clABCx171bE/s1024/Sunset%252520Hills.jpg",
    @"https://lh4.googleusercontent.com/-7-EHhtQthII/URqvEYTk4vI/AAAAAAAAAbs/QSJZoB3YjVg/s1024/Tenaya%252520Lake%2525202.jpg",
    @"https://lh6.googleusercontent.com/-8MrjV_a-Pok/URqvFC5repI/AAAAAAAAAbs/9inKTg9fbCE/s1024/Tenaya%252520Lake.jpg",
    @"https://lh5.googleusercontent.com/-B1HW-z4zwao/URqvFWYRwUI/AAAAAAAAAbs/8Peli53Bs8I/s1024/The%252520Cave%252520BW.jpg",
    @"https://lh3.googleusercontent.com/-PO4E-xZKAnQ/URqvGRqjYkI/AAAAAAAAAbs/42nyADFsXag/s1024/The%252520Fisherman.jpg",
    @"https://lh4.googleusercontent.com/-iLyZlzfdy7s/URqvG0YScdI/AAAAAAAAAbs/1J9eDKmkXtk/s1024/The%252520Night%252520is%252520Coming.jpg",
    @"https://lh6.googleusercontent.com/-G-k7YkkUco0/URqvHhah6fI/AAAAAAAAAbs/_taQQG7t0vo/s1024/The%252520Road.jpg",
    @"https://lh6.googleusercontent.com/-h-ALJt7kSus/URqvIThqYfI/AAAAAAAAAbs/ejiv35olWS8/s1024/Tokyo%252520Heights.jpg",
    @"https://lh5.googleusercontent.com/-Hy9k-TbS7xg/URqvIjQMOxI/AAAAAAAAAbs/RSpmmOATSkg/s1024/Tokyo%252520Highway.jpg",
    @"https://lh6.googleusercontent.com/-83oOvMb4OZs/URqvJL0T7lI/AAAAAAAAAbs/c5TECZ6RONM/s1024/Tokyo%252520Smog.jpg",
    @"https://lh3.googleusercontent.com/-FB-jfgREEfI/URqvJI3EXAI/AAAAAAAAAbs/XfyweiRF4v8/s1024/Tufa%252520at%252520Night.jpg",
    @"https://lh4.googleusercontent.com/-vngKD5Z1U8w/URqvJUCEgPI/AAAAAAAAAbs/ulxCMVcU6EU/s1024/Valley%252520Sunset.jpg",
    @"https://lh6.googleusercontent.com/-DOz5I2E2oMQ/URqvKMND1kI/AAAAAAAAAbs/Iqf0IsInleo/s1024/Windmill%252520Sunrise.jpg",
    @"https://lh5.googleusercontent.com/-biyiyWcJ9MU/URqvKculiAI/AAAAAAAAAbs/jyPsCplJOpE/s1024/Windmill.jpg",
    @"https://lh4.googleusercontent.com/-PDT167_xRdA/URqvK36mLcI/AAAAAAAAAbs/oi2ik9QseMI/s1024/Windmills.jpg",
    @"https://lh5.googleusercontent.com/-kI_QdYx7VlU/URqvLXCB6gI/AAAAAAAAAbs/N31vlZ6u89o/s1024/Yet%252520Another%252520Rockaway%252520Sunset.jpg",
    @"https://lh4.googleusercontent.com/-e9NHZ5k5MSs/URqvMIBZjtI/AAAAAAAAAbs/1fV810rDNfQ/s1024/Yosemite%252520Tree.jpg",
    @"https://lh4.googleusercontent.com/-e9NHZ5k5MSs/URqvMIBZjtI/AAAAAAAAAbs/1fV810rDNfQ/s1024/Yosemite%252520Tree.jpg"};


@implementation AlbumContentsViewController

#pragma mark - View lifecycle

- (void)viewDidLoad {
    
    [super viewDidLoad];
    
    self.title = [[NSBundle mainBundle] objectForInfoDictionaryKey:@"CFBundleName"];
    
    // Configuring the appearance of the bars
    // NB: status bar is always hidden. See the two keys in plist file: "View controller-based status bar appearance"
    // and "Status bar is initially hidden"
    
    // bgColor and textColor have to be contrasted
    UIColor * bgColor = [UIColor blackColor];
    UIColor * textColor = [UIColor whiteColor];
    
    self.collectionView.backgroundColor = bgColor;
    self.navigationController.navigationBar.backgroundColor = [UIColor colorWithRed:0 green:0 blue:0 alpha:0.4];
    
    self.navigationController.navigationBar.translucent = YES;
    [self.navigationController.navigationBar setBackgroundImage:[UIImage new] forBarMetrics:UIBarMetricsDefault];
    
    [self.navigationController.navigationBar
     setTitleTextAttributes:@{NSForegroundColorAttributeName : textColor}];
    
    //add a button to refresh the list of images
    [self initRightBarButton:0];
    
    //initialize the list of the urls
    self.urlImages =[[NSMutableArray alloc]init];
    
    if (!self.assets) {
        _assets = [[NSMutableArray alloc] init];
        
        //
        if ([DMAProperties getDMAProp].localImages) {
            // Images are provided within the bundle customImg.bundle
            [self initLocalImages];
        } else {
            // There is a back-end: app has to call a rest webservice to fill out the list or urls
            [self refreshImages:nil];
            
            //[self initurlImages]; // To test with a local list of 100 urls/images
        }
    }
    
    //initialize App Theme
    [self setTheme:[DMAProperties getDMAProp].appTheme];
}

// mode = 0 - standard button action
// mode = 1 - WIP button
-(void) initRightBarButton:(int) mode {
    
    // No access to a server is available in this mode
    if ([DMAProperties getDMAProp].localImages == YES) return;
    
    UIBarButtonItem * newBarButton;
    if (!mode) {
        newBarButton = [[UIBarButtonItem alloc] initWithTitle:@"Refresh"
                                                        style:UIBarButtonItemStyleDone target:self action:@selector(refreshImages:)];
    } else {
        // WIP button
        CGRect frame = CGRectMake(0.0, 0.0, 25.0, 25.0);
        UIActivityIndicatorView *loading = [[UIActivityIndicatorView alloc] initWithFrame:frame];
        [loading startAnimating];
        [loading sizeToFit];
        loading.autoresizingMask = (UIViewAutoresizingFlexibleLeftMargin |
                                    UIViewAutoresizingFlexibleRightMargin |
                                    UIViewAutoresizingFlexibleTopMargin |
                                    UIViewAutoresizingFlexibleBottomMargin);
        // initing the bar button  UIButton
        newBarButton = [[UIBarButtonItem alloc] initWithCustomView:loading];
    }
    self.navigationItem.rightBarButtonItem = newBarButton;
}

- (void)viewWillAppear:(BOOL)animated {
   [super viewWillAppear:animated];
    
}

- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    [self.collectionView reloadData];
}

//handle init custom images here
-(void) initLocalImages {
    //find the bundle
    NSString * bundlePath =  [[NSBundle mainBundle] pathForResource:@"customImg" ofType:@"bundle"];
    NSBundle * bundle = [NSBundle bundleWithPath:bundlePath];
    
    //find image by extension
    NSArray* myJpgImages = [bundle pathsForResourcesOfType:@"jpg" inDirectory:nil];
    NSArray* myPngImages = [bundle pathsForResourcesOfType:@"png" inDirectory:nil];
    NSArray* myJpegImages = [bundle pathsForResourcesOfType:@"jpeg" inDirectory:nil];
    
    //fill assets with custom images
    for (int i = 0 ; i< (int)[myJpgImages count] ; i++) {
        NSString *imageName = myJpgImages[i];
        UIImage *image = [UIImage imageNamed:imageName];
        [self.assets addObject:image];
    }
    
    for (int i = 0 ; i< (int)[myPngImages count] ; i++) {
        NSString *imageName = myPngImages[i];
        UIImage *image = [UIImage imageNamed:imageName];
        [self.assets addObject:image];
    }
    
    for (int i = 0 ; i< (int)[myJpegImages count] ; i++) {
        NSString *imageName = myJpegImages[i];
        UIImage *image = [UIImage imageNamed:imageName];
        [self.assets addObject:image];
    }
}

// use this method if you want to test the app with a list of 100 urls/images
-(void) initurlImages {
   for (int i = 0 ; i < 100 ; i++) {
        [self.urlImages addObject:defaultStringUrl[i]];
        [self.assets addObject:[UIImage imageNamed:@"Placeholder.png"]];
    }
    
    // load image in a different thread
    [NSThread detachNewThreadSelector: @selector(asynchImageLoading) toTarget:self withObject:nil];
}

//asynchronous loading of images
-(void) asynchImageLoading{
    for (int i = 0 ; i < (int)[self.urlImages count] ; i++) {
        NSString *imageUrlString = [self.urlImages objectAtIndex:i];
        NSURL *url = [NSURL URLWithString:imageUrlString];
        NSData *data = [[NSData alloc] initWithContentsOfURL:url];
        UIImage *image = [UIImage imageWithData:data];
        // if an error occurs (ie: no network), the image is not loaded
        if (image != nil) {
            [self.assets replaceObjectAtIndex:i withObject:image];
            [self performSelectorOnMainThread:@selector(refreshUI) withObject:nil waitUntilDone:NO];
        }
    }
}

-(void) setTheme:(NSString*) appTheme {
    UIColor * barTintColor = nil;
    UIColor * titleColor = nil;
    UIColor * collectionBack = nil;
    
    
    if ([appTheme isEqualToString:@"theme-blue"]) {
        barTintColor = [UIColor colorWithRed:46.0/255.0 green:63.0/255.0 blue:174.0/255.0 alpha:1.0];
        titleColor = [UIColor whiteColor];
        collectionBack = [UIColor colorWithRed:63.0/255.0 green:89.0/255.0 blue:253.0/255.0 alpha:1.0];
    }
    else if ([appTheme isEqualToString:@"theme-orange"]) {
        barTintColor = [UIColor colorWithRed:189.0/255.0 green:106.0/255.0 blue:0.0/255.0 alpha:1.0];
        titleColor = [UIColor whiteColor];
        collectionBack = [UIColor colorWithRed:235.0/255.0 green:134.0/255.0 blue:0.0/255.0 alpha:1.0];
    }
    else {
        barTintColor = [UIColor colorWithRed:10.0/255.0 green:10.0/255.0 blue:10.0/255.0 alpha:1.0];
        titleColor = [UIColor whiteColor];
        collectionBack = [UIColor colorWithRed:50.0/255.0 green:50.0/255.0 blue:50.0/255.0 alpha:1.0];
    }
    
    [self.navigationController.navigationBar setBarTintColor:barTintColor];
    [self.navigationController.navigationBar setTitleTextAttributes:@{NSForegroundColorAttributeName : titleColor}];
    self.collectionView.backgroundColor = collectionBack;
}

-(void) refreshUI {
    [self.collectionView reloadData];
}


-(void) refreshImages:(id) sender {
    
    // 1. create the url
    NSString *string = [NSString stringWithFormat:@"%@image/list", [DMAProperties getDMAProp].backendUrl];
    NSURL *url = [NSURL URLWithString:string];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    
    // 2. access to the url
    AFHTTPRequestOperation *operation = [[AFHTTPRequestOperation alloc] initWithRequest:request];
    operation.responseSerializer = [AFJSONResponseSerializer serializer];
    
    [operation setCompletionBlockWithSuccess:^(AFHTTPRequestOperation *thisOperation, id responseObject) {
        
        // 3. Process the answer
        NSDictionary * dictionary = nil;
        dictionary = (NSDictionary *)responseObject;
        
        id arrayOf = nil;
        arrayOf = [dictionary objectForKey:@"thumbnails" ];
        if (arrayOf == nil) return;
        
        [self.urlImages removeAllObjects];
        [self.assets removeAllObjects];
        for (NSString * currentImage in arrayOf) {
            [self.assets addObject:[UIImage imageNamed:@"Placeholder.png"]];
            [self.urlImages addObject:[NSString stringWithFormat:@"%@image/%@", [DMAProperties getDMAProp].backendUrl, currentImage]];
        }
        //
        // load image in a different thread
        [self initRightBarButton:0];
        [NSThread detachNewThreadSelector: @selector(asynchImageLoading) toTarget:self withObject:nil];
        
    } failure:^(AFHTTPRequestOperation *thisOperation, NSError *error) {
        
        // 3bis. process the error
        [self displayErrorTypeFor:thisOperation withCustomMsg:@"Error retrieving the list of images"];
        
        
    }];
    
    // 4. Launch the request asynchronously
    [operation start];
}

-(void) displayErrorTypeFor:(AFHTTPRequestOperation *) thisOperation withCustomMsg:(NSString *) customMsg {
    
    UIAlertView *alertView;
    NSInteger httpCode = thisOperation.response.statusCode;
    
    if ((httpCode == 404)||(httpCode == 0)) {
        alertView = [[UIAlertView alloc] initWithTitle:customMsg
                                               message:
                     [NSString stringWithFormat:@"The cloud dedicated instance is probably permanently down.\nCheck your network and this url to be sure:\n%@status\n\nYou can recycle your mobile app and generate a new one at \nhttp://mygallery.designmyapp.mobi", [DMAProperties getDMAProp].backendUrl]
                                              delegate:nil
                                     cancelButtonTitle:@"Ok"
                                     otherButtonTitles:nil];
        
    } else {
        alertView = [[UIAlertView alloc] initWithTitle:customMsg
                                               message:[NSString stringWithFormat:@"%@ (code=%i)",thisOperation.responseString, (int)httpCode]
                                              delegate:nil
                                     cancelButtonTitle:@"Ok"
                                     otherButtonTitles:nil];
        
    }
    [alertView show];
}

#pragma mark - UICollectionViewDelegate

- (NSInteger)collectionView:(UICollectionView *)view numberOfItemsInSection:(NSInteger)section {
    
    return self.assets.count;
}

#define kImageViewTag 1 // the image view inside the collection view cell prototype is tagged with "1"

- (UICollectionViewCell *)collectionView:(UICollectionView *)cv cellForItemAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *CellIdentifier = @"photoCell";
    
    UICollectionViewCell *cell = [cv dequeueReusableCellWithReuseIdentifier:CellIdentifier forIndexPath:indexPath];
    
    UIImage * img = self.assets[indexPath.row];
    UIImageView *imageView = (UIImageView *)[cell viewWithTag:kImageViewTag];
    imageView.image = img;

    return cell;
}


#pragma mark - Segue support

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    
    if ([[segue identifier] isEqualToString:@"showPhoto"]) {
        
        // hand off the assets of this album to our singleton data source
        [PageViewControllerData sharedInstance].photoAssets = self.assets;
        
        // start viewing the image at the appropriate cell index
        MyPageViewController *pageViewController = [segue destinationViewController];
        NSIndexPath *selectedCell = [self.collectionView indexPathsForSelectedItems][0];
        pageViewController.startingIndex = selectedCell.row;
    }
}

// This following 3 methods are required to force status bar in hidden mode after UIImagePickerController has disappeared
-(void)navigationController:(UINavigationController *)navigationController
     willShowViewController:(UIViewController *)viewController
                   animated:(BOOL)animated
{
    [[UIApplication sharedApplication] setStatusBarHidden:YES];
    
}

-(BOOL)prefersStatusBarHidden   // iOS8 definitely needs this one. checked.
{ 
    return YES;
}

-(UIViewController *)childViewControllerForStatusBarHidden
{
    return nil;
}


@end

