s = input()

ok = True
for i in range(1, 16, 2):
    ok = s[i] == '0' and ok
    
print('Yes' if ok else 'No')