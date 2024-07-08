import string
s = input()
alphabets = {i: 0 for i in string.ascii_lowercase}

for i in s:
    if i in alphabets:
        alphabets[i] += 1
f = True
c = 0
for i in range(1,101):
    for j in alphabets.values():
        if i == j:
            c += 1
    if not(c == 0 or c == 2):
        print("No")
        exit()
    c = 0
print("Yes")