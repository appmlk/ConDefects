s = input()
t = input()
for i in range(len(s)):
    if s[i] != t[i]:
        print(i + 1)
        exit()

print(1)
