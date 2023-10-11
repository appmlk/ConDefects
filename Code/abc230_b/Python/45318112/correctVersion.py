s = input()
if (len(s) == 2 and s == 'oo'):
    print('No')
    exit()
elif(len(s) == 2):
    print('Yes')
    exit()
if (len(s) == 1):
    print('Yes')
    exit()
if (s[0] == 'x'):
    s = s[1:]
if (s[0] == 'x'):
    s = s[1:]
if (s[0] == 'x'):
    print('No')
    exit()
m = ['o','x','x']
for i in range(len(s)):
    if (s[i] != m[i%3]):
        print('No')
        exit()
print('Yes')