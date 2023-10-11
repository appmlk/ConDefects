s, t = input() + "0", input()

for i in range(len(s)):
    if s[i] != t[i]:
        print(i+1)
        break
