n = int(input())
s = list(input())
a = s[:n // 2]
b = list(reversed(s[(n + 1) // 2:]))

def main():
    if a[0] != b[0]:
      if a[0] == "A":
         return False

    return True

if main():
   print("Yes")
else:
   print("No")        

