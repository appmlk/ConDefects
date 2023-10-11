n = int(input())

num = 'abcdef'

a = str(hex(n))

#print(a)

a = a[2:]

#print(a)

if len(a) < 2:
    if a in num:
        a = a.upper()
        a = '0'+a
        print(a)
        exit()
    else:
        print(a)
        exit()

else:
    b = []
    for i in range(len(a)):
        if a[i] in num:
            c = a[i].upper()
            b.append(c)
            #print(c)
        else:
            b.append(str(a[i]))
print(''.join(b))