//
//  DMAProperties.m
//  MyImagePicker
//
//  Created by Maxime Bibos on 25/03/2015.
//
//

#import "DMAProperties.h"

static DMAProperties * dMAProperties = nil;

@implementation DMAProperties


+(DMAProperties *) getDMAProp {
    return dMAProperties;
}

// Search Custom variable in the plist file
+(void) initDMAProperties {
    
    if (dMAProperties == nil)
        dMAProperties = [[DMAProperties alloc] init];
    
    //Create path to pathList to acces our customizable .plist
    NSError *error;
    NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    NSString *documentsDirectory = [paths objectAtIndex:0];
    NSString *path = [documentsDirectory stringByAppendingPathComponent:@"Custom.plist"];
    
    NSFileManager *fileManager = [NSFileManager defaultManager];
    
    if (![fileManager fileExistsAtPath: path]) {
        NSString *bundle = [[NSBundle mainBundle] pathForResource:@"Custom" ofType:@"plist"];
        
        [fileManager copyItemAtPath:bundle toPath: path error:&error];
    }
    
    //Read Data in .plist
    NSMutableDictionary *savedStock = [[NSMutableDictionary alloc] initWithContentsOfFile: path];
    
    dMAProperties.localImages = [[savedStock objectForKey:@"localImages"] boolValue];
    dMAProperties.appTheme = [savedStock objectForKey:@"appTheme"];
    dMAProperties.enableLocalStorage = [[savedStock objectForKey:@"enableLocalStorage"] boolValue];
}


@end
