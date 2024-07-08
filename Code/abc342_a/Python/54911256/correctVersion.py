s= input()

if s.count(s[0])==1:
        print(1)
else:
    for i in range(1,len(s)):
        if s[0] != s[i]:
            print(i+1)