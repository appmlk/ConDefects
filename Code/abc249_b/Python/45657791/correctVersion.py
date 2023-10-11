s = list(input())
big = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
small = "abcdefghijklmnopqrstuvwxyz"
isBig = False
isSmall = False
isDifferent = True
for i in range(len(s)):
    if(s[i] in big):
        isBig = True
    if(s[i] in small):
        isSmall = True
    if(s.count(s[i]) > 1):
        isDifferent = False
        break
if(isBig == True and isSmall == True and isDifferent == True):
    print("Yes")
else:
    print("No")
