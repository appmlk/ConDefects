s = list(input())
max = 0
for i in range(len(s)):
    for j in range(i+1,len(s)+1):
        x = s[i:j]
        x.reverse()
        if s[i:j]==x and max<j-i:
            max = j-i
print("{}".format(max))