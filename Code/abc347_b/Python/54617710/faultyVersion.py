s = input()
S_part = set()
for i in range(len(s)):
    for j in range(i+1,len(s)+1):
        S_part.add(s[i:j])
print(S_part)