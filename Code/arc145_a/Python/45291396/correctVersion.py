n = int(input())
s = list(input())

if (s[0]=='A') and (s[-1]=='B'):
    print('No')
elif (len(s)==2) and (s[0]!=s[-1]):
    print('No')
else:
    print('Yes')