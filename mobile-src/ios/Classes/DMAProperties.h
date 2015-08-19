//
//  DMAProperties.h
//  MyImagePicker
//
//  Created by Maxime Bibos on 25/03/2015.
//
//

#import <Foundation/Foundation.h>

@interface DMAProperties : NSObject

//generation properties
@property (nonatomic, assign) BOOL localImages;    // YES: no-backend, local images only - NO: back-end, url images only
@property (nonatomic, retain) NSString * appTheme;
@property (nonatomic, assign) BOOL enableLocalStorage; // YES: an action is available to store in the personal library the images
@property (nonatomic, retain) NSString * backendUrl;
@property (nonatomic, assign) BOOL addImage;


+(DMAProperties *) getDMAProp;
+(void) initDMAProperties;

@end