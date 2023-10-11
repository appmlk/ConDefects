'''
・XX, XY, YX, YYは必ず 重なる
①XY, YXについて 数の差が2以上になると実現不可能
    XY1 YX2 = YXYX に、もうひとつ「YX」をつけようとすると
    ・後ろにつけようとする YXYXYX → XYが発生
    ・前につけようとする YXYXYX → XYが発生
②XX, YYどちらもあって、YX, XYどちらもないは実現不可能
    ・XXYY → XYが発生
    ・YYXX → YXが発生
'''

n, xx, xy, yx, yy = map(int, input().split())

flg = 1
if abs(xy - yx) >= 2:
    flg = 0
if xx > 0 and yy > 0 and yx == 0 and xy == 0:
    flg = 0
    
print('Yes' if flg else 'No')