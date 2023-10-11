s = input()
t = input()
ls = []
lt = []
c = 1
for i in range(len(s)):
    if i != len(s)-1 and s[i] == s[i+1]:
        c += 1
    else:
        ls.append([s[i],c])
        c = 1
c = 1
for i in range(len(t)):
    if i != len(t)-1 and t[i] == t[i+1]:
        c += 1
    else:
        lt.append([t[i],c])
        c = 1
if len(ls) != len(lt):
    print("No")
else:
    for i in range(len(ls)):
        if ls[i][0] != lt[i][0] or (ls[i][1] == 1 and lt[i][1] > 1) or (ls[i][1] > lt[i][1]):
            print("No")
            break
    else:
        print("Yes")