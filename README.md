# PicScrabble
 Instructions
·  Your are basically necessitated to create a memory game
·  For this you will load 9 images from flickr’s api onto a grid view
·  The user gets 15 seconds to remember the images
·  Once 15 seconds pass by, the images are flipped over
·  The user is then presented with one of the 9 images, and is asked to point out its location.
·  This image should be presented elsewhere, perhaps above or below the grid, and should be displayed in a random fashion
·  Once the image is correctly identified, it is flipped back
·  The current turn is not complete until the user identifies the correct image
·  The game ends when all 9 images have been flipped back
·  All images, should be fetched from Flickr’s public api : 
	
  String BASE_URL = "http://api.flickr.com";
  
  String FEED_API = /services/feeds/photos_public.gne?&lang=en-us&format=json&nojsoncallback=1";
