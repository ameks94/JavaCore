package com.labs;

public class CustomClassLoader extends ClassLoader {

    private byte[] classData;

    public CustomClassLoader(byte[] classData){
        super(getSystemClassLoader());
        this.classData = classData;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        if ( !name.startsWith( FileUtils.CLASS_PACKAGE ) ) {
            return super.loadClass( name );
        }

        Class definedClazz = defineClass( name,
                classData, 0, classData.length );

        if (definedClazz==null) {
            definedClazz = findSystemClass( name );
        }

        if ( definedClazz == null )
            throw new ClassNotFoundException();

        return  definedClazz;
    }

    public Class<?> getLoadedClass(String name) {
        return findLoadedClass(name);
    }
}
