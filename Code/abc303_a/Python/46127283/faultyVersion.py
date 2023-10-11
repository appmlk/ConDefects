n = int(input())
s = input()
t = input()

p = 0

for i in range(n):
    if s[i] == "1" or s[i] == "l":
        if t[i] == "1" or t[i] == "l":
            p += 1
        
    elif s[i] == "0" or t[i] == "o":
        if t[i] == "0" or t[i] == "o":
            p += 1
            
    elif s[i] == t[i]:
        p += 1
    
if p == n:
    print("Yes")
    
else:
    print("No")
    