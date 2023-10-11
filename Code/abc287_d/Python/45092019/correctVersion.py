s = str(input())
t = str(input())

pre = [False for i in range(len(t)+1)]
back = [False for i in range(len(t)+1)]

pre[0] = True
for i in range(1,len(t)+1):
    if s[i-1] == t[i-1] or (s[i-1] == "?" or t[i-1] == "?"):
        pre[i] = True
    else:
        break

back[len(t)] = True
for i in range(1,len(t)+1):
    if s[-i] == t[-i] or (s[-i] == "?" or t[-i] == "?"):
        back[-i-1] = True
    else:
        break

for i in range(len(t)+1):
    if pre[i]&back[i] == True:
        print("Yes")
    else:
        print("No")