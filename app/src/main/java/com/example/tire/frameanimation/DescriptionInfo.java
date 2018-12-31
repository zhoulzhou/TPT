package com.example.tire.frameanimation;

/**
 * 通过图片的URL从网络下载图片，将图片先缓存到内存缓存中，
 * 缓存到强引用也就是LruCache中。如果LruCache空间不足，
 * 就会将较早存储的图片对象淘汰到软引用缓存中，然后将图片缓存到文件中。
 * 在读取图片时，先读取内存缓存，判断LruCache是否存在图片，
 * 如果存在，则直接读取，如果LruCache中不存在，则判断软引用中是否存在，
 * 如果软引用中存在，则将软引用中的图片添加到LruCache并且删除软引用中的数据，
 * 如果软引用中不存在，则从文件或网络读取。
 */
public class DescriptionInfo {
}
