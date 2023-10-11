s = input()
count_r = 0
count_l = 0
while s[len(s)-1 - count_r] == "a":
    count_r += 1
    if count_r == len(s):
        break
while s[count_l] == "a":
    count_l += 1
    if count_l == len(s):
        break

sub = count_r - count_l

if sub < 0:
    print("No")
    exit(0)

l = 0
r = len(s) - 1 - sub

while s[l] == s[r]:
    if l >= r:
        print("Yes")
        exit(0)
    l += 1
    r -= 1

print("Yes")