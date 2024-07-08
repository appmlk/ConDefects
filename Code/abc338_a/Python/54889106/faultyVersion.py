s = input()

if s.islower():
    print("No")
    exit()
for i in range(1, len(s) - 1):
    if s[i].isupper():
        print("No")
        exit()

print("Yes")
