s = input()
t = input()

if len(s) > len(t) or (len(s) == len(t) and s != t):
    print("No")
    exit()
else:
    for i in range(len(s) - len(t) + 1):
        if s[i:i+len(t)] == t:
            print("Yes")
            exit()
print("No")