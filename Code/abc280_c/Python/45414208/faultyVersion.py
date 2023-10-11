s, t = input(), input()

for i in range(len(s)):
    if s[i] != t[i]:
        print(i+1)
        break
