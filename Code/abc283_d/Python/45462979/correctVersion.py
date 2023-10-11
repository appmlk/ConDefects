S = input()
t = set([])
a=[[]]
for i in S:
    if i.isalpha() == True:
        if i in t:
            print("No")
            exit()
        else:
            t.add(i)
            a[-1].append(i)
    elif i==")":
      #  print(a)
        for j in a[-1]:
         #   print(t)
            t.remove(j)
        a.pop(-1)
    elif i=="(":
        a.append([])


  #  print(t)
  #  print(a)

print("Yes")