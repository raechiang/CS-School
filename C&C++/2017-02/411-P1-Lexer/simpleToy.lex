boolean checkRange(int n)
{
    // checks if n is in the range [0,100]
    if (n >= 0 && n <= 100)
        return true;
    return false;
}

void main()
{
    // a weirdly named string
    string String = "Hello, world!";
    // This prints Hello, world!
    println(String);

    /* I'm not really sure what the purpose of this is */
    int i = 0;
    for (int i = 0; i < 200; i = i + 2)
    {
        if (checkRange(i))
        {
            printf("You are within range.");
        }
    }
}
