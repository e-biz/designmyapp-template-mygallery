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


+(DMAProperties *) getDMAProp;
+(void) initDMAProperties;

@end